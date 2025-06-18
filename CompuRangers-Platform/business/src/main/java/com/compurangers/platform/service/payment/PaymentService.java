package com.compurangers.platform.service.payment;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentService {

    public String generarPago(String email, String amount, String currency, String mode, String language, String orderId) {
    String tunnelUrl = "https://huge-mastiff-solid.ngrok-free.app"; //"https://huge-mastiff-solid.ngrok-free.app"; //Cambiar al tunel local 

    try {
        // Construcción del JSON
        String json = String.format("""
            {
                "email": "%s",
                "amount": "%s",
                "currency": "%s",
                "mode": "%s",
                "language": "%s",
                "orderId": "%s"
            }
            """, email, amount, currency, mode, language, orderId);

        // Realizar la solicitud HTTP POST
        URL url = new URL(tunnelUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.setDoOutput(true);

        // Enviar el JSON en el cuerpo de la solicitud
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Obtener la respuesta
        int status = connection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            // Leer la respuesta JSON
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            String redirectionUrl = jsonResponse.getString("redirectionUrl");

            System.out.println("URL de redirección: " + redirectionUrl);
            return redirectionUrl;
        } else {
            System.err.println("Error al generar el pago. Código de respuesta: " + status);
            return null; // Devolver null en caso de error
        }
    } catch (IOException | JSONException e) {
        System.err.println("Excepción en generarPago:");
        e.printStackTrace(); // <-- Muy importante para ver la causa real
        return null;
    }
}

    public void redireccionarAPago(String urlPago) {
        try {
            Desktop.getDesktop().browse(URI.create(urlPago));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("No se pudo redirigir al pago: " + e.getMessage());
        }
    }
}

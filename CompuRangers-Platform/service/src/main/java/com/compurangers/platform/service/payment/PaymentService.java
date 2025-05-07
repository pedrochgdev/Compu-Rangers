package com.compurangers.platform.service.payment;

import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class PaymentService {

    public String generarPago(String email, double amount, String currency, String mode, String language, String orderId) {
        // Aquí deberías construir el JSON y enviarlo a tu servidor que hace de intermediario con Izipay

        // Supongamos que usas una API intermedia tipo tunnel (como DevTunnels de VSCode)
        String tunnelUrl = "https://7mldmlxc-9090.brs.devtunnels.ms/";

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
            URL url = new URL(tunnelUrl);  // El servidor intermediario que recibe el POST
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Enviar el JSON en el cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obtener la respuesta
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                // Si la respuesta es correcta, retornar el URL de pago
                return tunnelUrl + "pago/" + orderId;
            } else {
                return "Error al generar el pago. Código de respuesta: " + status;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la solicitud de pago";
        }
    }

    public void redireccionarAPago(String urlPago) {
        try {
            Desktop.getDesktop().browse(URI.create(urlPago));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

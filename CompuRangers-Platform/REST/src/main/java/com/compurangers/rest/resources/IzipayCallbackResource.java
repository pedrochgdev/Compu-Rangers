package com.compurangers.rest.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import com.compurangers.platform.util.Config;

@Path("/izipay-callback")
public class IzipayCallbackResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response recibirNotificacion(MultivaluedMap<String, String> form) {

        String usedMerchantKey = Config.getEnv("KEY_IZIPAY"); // Debe ser la misma usada en signature

        // 1. Verificar firma
        String firmaRecibida = form.getFirst("signature");
        if (firmaRecibida == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Falta signature").build();
        }

        String firmaCalculada = calcularFirma(form, usedMerchantKey);
        if (!firmaRecibida.equals(firmaCalculada)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Firma no válida").build();
        }

        // 2. Verificar estado de la transacción
        String estado = form.getFirst("vads_trans_status");
        if (!"AUTHORISED".equalsIgnoreCase(estado)) {
            return Response.ok("Transacción no autorizada, ignorada").build();
        }

        // 3. Obtener datos relevantes
        String orderId = form.getFirst("vads_order_id");
        String amount = form.getFirst("vads_amount");

        // 4. Aquí deberías actualizar tu base de datos (ejemplo simbólico)
        System.out.printf("✅ Orden %s pagada con éxito por %s centavos%n", orderId, amount);
        // ejemplo: ordenVentaDAO.marcarComoPagada(orderId);

        return Response.ok("OK").build();
    }

    private String calcularFirma(MultivaluedMap<String, String> form, String clave) {
        List<String> keys = new ArrayList<>(form.keySet());
        keys.remove("signature");
        Collections.sort(keys);

        StringBuilder data = new StringBuilder();
        for (String key : keys) {
            List<String> values = form.get(key);
            if (values != null && !values.isEmpty()) {
                data.append(values.get(0)).append("+");
            }
        }
        data.append(clave);

        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "RAW");
            sha256_HMAC.init(secretKey);
            byte[] hash = sha256_HMAC.doFinal(data.toString().getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular la firma", e);
        }
    }
}

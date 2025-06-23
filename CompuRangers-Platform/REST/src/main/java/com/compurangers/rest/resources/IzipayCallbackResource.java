package com.compurangers.rest.resources;

import com.compurangers.platform.dao.mysql.sales.OrdenDeVentaDAOImpl;
import com.compurangers.platform.service.sales.OrdenDeVentaBO;
import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.mysql.financial.MetodoPagoDAOImpl;
import com.compurangers.platform.service.financial.MetodoPagoBO;
import com.compurangers.platform.core.domain.financial.MetodoPago;
import com.compurangers.platform.core.domain.financial.Pago;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.dao.mysql.configuration.MonedaPeriodoDAOImpl;
import com.compurangers.platform.core.domain.configuration.MonedaPeriodo;
import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.core.domain.sales.DetalleVenta;
import com.compurangers.platform.dao.mysql.sales.DocumentoDeVentasDAOImpl;
import com.compurangers.platform.service.configuration.MonedaPeriodoBO;
import com.compurangers.platform.service.sales.DocumentoDeVentasBO;
import com.compurangers.platform.core.domain.sales.DocumentoDeVentas;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.mysql.financial.PagoDAOImpl;
import com.compurangers.platform.dao.mysql.inventory.DetalleLoteDAOImpl;
import com.compurangers.platform.dao.mysql.inventory.InventarioDAOImpl;
import com.compurangers.platform.dao.mysql.sales.CarritoDAOImpl;
import com.compurangers.platform.dao.mysql.sales.DetalleVentaDAOImpl;
import com.compurangers.platform.dao.mysql.sales.ItemCarritoDAOImpl;
import com.compurangers.platform.service.catalog.ProductoBO;
import com.compurangers.platform.service.financial.PagoBO;
import com.compurangers.platform.service.inventory.DetalleLoteBO;
import com.compurangers.platform.service.inventory.InventarioBO;
import com.compurangers.platform.service.sales.CarritoBO;
import com.compurangers.platform.service.sales.DetalleVentaBO;
import com.compurangers.platform.service.sales.ItemCarritoBO;
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
        String usedMerchantKey = Config.getEnv("KEY_IZIPAY");

        String firmaRecibida = form.getFirst("signature");
        if (firmaRecibida == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Falta signature").build();
        }

        String firmaCalculada = calcularFirma(form, usedMerchantKey);
        if (!firmaRecibida.equals(firmaCalculada)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Firma no válida").build();
        }

        String estado = form.getFirst("vads_trans_status");
        String orderId = form.getFirst("vads_order_id");
        String amount = form.getFirst("vads_amount");
        String transId = form.getFirst("vads_trans_id");
        
        int id = Integer.parseInt(orderId.substring(6));
        switch (estado) {
            case "AUTHORISED":
                procesarPagoExitoso(id, transId);
                break;
            case "ABANDONED":
                procesarPagoFallido(id, estado);
                break;
            case "REFUSED":
                procesarPagoFallido(id, estado);
                break;
            case "CANCELLED":
                procesarPagoFallido(id, estado);
                break;
            default:
                System.out.printf("📄 Orden %s recibió estado desconocido: %s%n", orderId, estado);
                break;
        }

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
    
    private void crearPago(int numDocVenta, double total, String transId){
        MetodoPagoBO mp = new MetodoPagoBO(new MetodoPagoDAOImpl());
        MetodoPago metodo = mp.searchByName("IZIPAY");
        MonedaPeriodoBO mpb = new MonedaPeriodoBO(new MonedaPeriodoDAOImpl());
        MonedaPeriodo mpc = mpb.searchWithType(1, "VENTA");
        PagoBO pbo = new PagoBO(new PagoDAOImpl());
        Pago p = new Pago();
        
        p.setDocumentoDeVentasNumero(numDocVenta);
        p.setEstado("COMPLETADO");
        p.setFechaPago(new Date());
        p.setMetodoDePagoId(metodo.getId());
        p.setMonedaPeriodoId(mpc.getId());
        p.setMonto(total);
        p.setReferencia(transId);
        
        int x = pbo.addPago(p);
        
        System.out.printf("✅ PAGO %d CREADO", x);
    }
    
    private int generarDocVenta(OrdenDeVenta order){
        DocumentoDeVentasBO dvbo = new DocumentoDeVentasBO(new DocumentoDeVentasDAOImpl());
        DocumentoDeVentas dv = new DocumentoDeVentas();
        dv.setOrdenDeVentaId(order.getId());
        dv.setTotal(order.getTotal());
        dv.setSubtotal(order.getTotal()/ (1+0.18));
        dv.setImpuestos(order.getTotal()-dv.getSubtotal());        
        return dvbo.addDocumentoVenta(dv);
    }
    
    private void eliminarCarrito(OrdenDeVenta ov){
        CarritoBO cbo = new CarritoBO(new CarritoDAOImpl());
        ItemCarritoBO icbo = new ItemCarritoBO(new ItemCarritoDAOImpl());
        
        Carrito carrito = cbo.getCarritoFromUser(ov.getClienteId());
        icbo.deleteAllByCarritoId(carrito.getId());
        
    }
    
    private void actualizarStockVendidos(int orderId) {
        DetalleVentaBO detalleVentaBO = new DetalleVentaBO(new DetalleVentaDAOImpl());
        ProductoBO productoBO = new ProductoBO(new ProductoDAOImpl());

        List<DetalleVenta> detalles = detalleVentaBO.getAllDetalleVentaFromOrder(orderId);

        for (DetalleVenta detalle : detalles) {
            Producto producto = detalle.getProducto(); // Asumiendo que DetalleVenta ya tiene el producto cargado
            int cantidadVendida = detalle.getCantidad();

            // Actualizar cantidad vendida acumulada
            int vendidosActual = producto.getCantidadVendida(); 
            producto.setCantidadVendida(vendidosActual + cantidadVendida);

            productoBO.updateProducto(producto);
        }

        System.out.printf("🛒 Productos actualizados para orden %d%n", orderId);
    }
    
    private void procesarPagoExitoso(int orderId, String transId) {
        OrdenDeVentaBO dao = new OrdenDeVentaBO(new OrdenDeVentaDAOImpl());
        OrdenDeVenta ov = dao.searchOrdenDeVenta(orderId);
        actualizarStockVendidos(orderId);
        int numDocVenta = generarDocVenta(ov);
        crearPago(numDocVenta, ov.getTotal(), transId);
        eliminarCarrito(ov);
        ov.setEstado("PAGADO");
        dao.updateOrdenDeVenta(ov);
//        reducirFromInventario(ov);
        System.out.printf("✅ Orden %d autorizada y será marcada como PAGADO%n", orderId);
    }
    
    private void reponerInventario(OrdenDeVenta ov) {
        InventarioBO invBO = new InventarioBO(new InventarioDAOImpl());
        DetalleLoteBO detalleLoteBO = new DetalleLoteBO(new DetalleLoteDAOImpl());
        
        DetalleVentaBO dvbo = new DetalleVentaBO(new DetalleVentaDAOImpl());
        
        System.out.printf("✅ Orden %d id", ov.getId());
        
        ov.setDetalles(dvbo.getAllDetalleVentaFromOrder(ov.getId()));

        for (DetalleVenta detalle : ov.getDetalles()) {
            int cantidadAReponer = detalle.getCantidad();
            int productoId = detalle.getProducto().getId();

            // Lotes en orden inverso (más recientes primero)
            List<Inventario> inventarios = invBO.getInventarioPorProductoDesc(productoId);

            for (Inventario inv : inventarios) {
                if (cantidadAReponer <= 0) break;

                int loteId = inv.getLoteId();
                int disponible = inv.getCantidadDisponible();

                // Stock máximo permitido en este lote (según lo que compraste originalmente)
                int stockOriginal = detalleLoteBO.getCantidadPorProductoYLote(productoId, loteId);

                int espacioDisponible = stockOriginal - disponible;
                int aAgregar = Math.min(espacioDisponible, cantidadAReponer);

                if (aAgregar > 0) {
                    inv.setCantidadDisponible(disponible + aAgregar);
                    invBO.updateInventario(inv);
                    cantidadAReponer -= aAgregar;
                }
            }

            if (cantidadAReponer > 0) {
                System.err.printf("⚠️ No se pudo reponer completamente el producto ID %d, faltan %d unidades%n", productoId, cantidadAReponer);
            }
        }
    }


    private void procesarPagoFallido(int orderId, String estado) {
        OrdenDeVentaBO dao = new OrdenDeVentaBO(new OrdenDeVentaDAOImpl());
        OrdenDeVenta ov = dao.searchOrdenDeVenta(orderId);
        reponerInventario(ov);
        dao.deleteOrdenDeVenta(orderId);
        System.out.printf("🗑️ Orden %d será eliminada debido a estado %s%n", orderId, estado);
    }
}

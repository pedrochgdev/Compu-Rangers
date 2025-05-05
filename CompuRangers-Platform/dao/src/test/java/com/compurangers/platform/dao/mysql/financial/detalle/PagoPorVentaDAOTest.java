package com.compurangers.platform.dao.mysql.financial.detalle;

import com.compurangers.platform.core.domain.financial.Pago;
import com.compurangers.platform.dao.financial.IPagoDAO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;

public class PagoPorVentaDAOTest {

    private final int documentoVentaId = 1001; // ID de un documento de venta con pagos asociados
    private final int documentoVentaInexistente = 99999; // ID de un documento de venta sin pagos

    @Test
    @Order(1)
    public void shouldGetPagosByDocumentoVentaIfExists() {
        IPagoDAO dao = new PagoPorVentaDAOImpl();
        List<Pago> pagos = dao.getAllByForeignKey(documentoVentaId);
        assertNotNull(pagos);
        assertFalse(pagos.isEmpty(), "La lista de pagos no debería estar vacía para un documento de venta existente");
        assertTrue(pagos.stream().allMatch(pago -> pago.getDocumentoDeVentasNumero() == documentoVentaId),
                "Todos los pagos deben estar asociados al documento de venta especificado");
        assertTrue(pagos.stream().allMatch(pago -> pago.getDocumentoDeComprasNumero() == 0),
                "Ningún pago debe estar asociado a un documento de compra");
    }

    @Test
    @Order(2)
    public void shouldNotGetPagosByDocumentoVentaIfNotExists() {
        IPagoDAO dao = new PagoPorVentaDAOImpl();
        List<Pago> pagos = dao.getAllByForeignKey(documentoVentaInexistente);
        assertNotNull(pagos, "La lista de pagos no debería ser null");
        assertTrue(pagos.isEmpty(), "La lista de pagos debería estar vacía para un documento de venta inexistente");
    }
    
}
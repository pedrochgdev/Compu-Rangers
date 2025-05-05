package com.compurangers.platform.dao.mysql.financial.detalle;

import com.compurangers.platform.core.domain.financial.Pago;
import com.compurangers.platform.dao.financial.IPagoDAO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;

public class PagoPorCompraDAOTest {

    private final int documentoCompraId = 2001; // ID de un documento de compra con pagos asociados
    private final int documentoCompraInexistente = 99999; // ID de un documento de compra sin pagos

    @Test
    @Order(1)
    public void shouldGetPagosByDocumentoCompraIfExists() {
        IPagoDAO dao = new PagoPorCompraDAOImpl();
        List<Pago> pagos = dao.getAllByForeignKey(documentoCompraId);
        assertNotNull(pagos);
        assertFalse(pagos.isEmpty(), "La lista de pagos no debería estar vacía para un documento de compra existente");
        assertTrue(pagos.stream().allMatch(pago -> pago.getDocumentoDeComprasNumero() == documentoCompraId),
                "Todos los pagos deben estar asociados al documento de compra especificado");
        assertTrue(pagos.stream().allMatch(pago -> pago.getDocumentoDeVentasNumero() == 0),
                "Ningún pago debe estar asociado a un documento de venta");
    }

    @Test
    @Order(2)
    public void shouldNotGetPagosByDocumentoCompraIfNotExists() {
        IPagoDAO dao = new PagoPorCompraDAOImpl();
        List<Pago> pagos = dao.getAllByForeignKey(documentoCompraInexistente);
        assertNotNull(pagos, "La lista de pagos no debería ser null");
        assertTrue(pagos.isEmpty(), "La lista de pagos debería estar vacía para un documento de compra inexistente");
    }
    
}
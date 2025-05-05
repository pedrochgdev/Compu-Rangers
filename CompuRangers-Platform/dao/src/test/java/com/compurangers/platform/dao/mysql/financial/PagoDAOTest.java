package com.compurangers.platform.dao.mysql.financial;

import com.compurangers.platform.core.domain.financial.Pago;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.financial.IPagoDAO;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PagoDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IPagoDAO dao = new PagoDAOImpl();

        Pago pago = new Pago();
        pago.setMonto(4500.75);
        pago.setFechaPago(new java.sql.Date(System.currentTimeMillis()));
        pago.setEstado("PENDIENTE");
        pago.setReferencia("CC99999");
        pago.setDocumentoDeVentasNumero(0);
        pago.setDocumentoDeComprasNumero(2001);
        pago.setMetodoDePagoId(1);
        pago.setMonedaPeriodoId(1);

        testId = dao.add(pago);
        assertTrue(testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldFindIfIdExists() {
        IPagoDAO dao = new PagoDAOImpl();
        Pago pago = dao.search(testId);
        assertNotNull(pago);
        assertEquals("PENDIENTE", pago.getEstado());
        assertEquals("CC99999", pago.getReferencia());
    }

    @Test
    @Order(3)
    @Override
    public void shouldUpdateIfIdExists() {
        IPagoDAO dao = new PagoDAOImpl();

        Pago pago = new Pago();
        pago.setId(testId);
        pago.setMonto(999.99);
        pago.setFechaPago(new java.sql.Date(System.currentTimeMillis()));
        pago.setEstado("COMPLETADO");
        pago.setReferencia("ACTUALIZADO123");
        pago.setDocumentoDeVentasNumero(1001);
        pago.setDocumentoDeComprasNumero(0);
        pago.setMetodoDePagoId(1);
        pago.setMonedaPeriodoId(1);

        boolean updated = dao.update(pago);
        assertTrue(updated);

        Pago actualizado = dao.search(testId);
        assertEquals("ACTUALIZADO123", actualizado.getReferencia());
        assertEquals("COMPLETADO", actualizado.getEstado());
        assertEquals(999.99, actualizado.getMonto());
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IPagoDAO dao = new PagoDAOImpl();

        Pago pago = new Pago();
        pago.setId(idIncorrecto);
        pago.setMonto(123.45);
        pago.setFechaPago(new java.sql.Date(System.currentTimeMillis()));
        pago.setEstado("FALLIDO");
        pago.setReferencia("NOEXISTE");
        pago.setDocumentoDeVentasNumero(1002);
        pago.setDocumentoDeComprasNumero(0);
        pago.setMetodoDePagoId(1);
        pago.setMonedaPeriodoId(1);

        boolean updated = dao.update(pago);
        assertFalse(updated);
    }

    @Test
    @Order(5)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IPagoDAO dao = new PagoDAOImpl();
        Pago pago = dao.search(idIncorrecto);
        assertNull(pago);
    }

    @Test
    @Order(6)
    @Override
    public void shouldList() {
        IPagoDAO dao = new PagoDAOImpl();
        List<Pago> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(7)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IPagoDAO dao = new PagoDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IPagoDAO dao = new PagoDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);

        Pago pago = dao.search(testId);
        assertNull(pago);
    }
    
}

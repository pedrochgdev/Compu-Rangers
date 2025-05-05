package com.compurangers.platform.dao.mysql.financial;

import com.compurangers.platform.core.domain.financial.MetodoPago;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.financial.IMetodoPagoDAO;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MetodoPagoDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IMetodoPagoDAO dao = new MetodoPagoDAOImpl();
        MetodoPago modelo = new MetodoPago();
        modelo.setNombre("Tarjeta Débito");
        modelo.setDescripcion("Pago con tarjeta de débito");
        modelo.setEstado("ACTIVO");

        this.testId = dao.add(modelo);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldFindIfIdExists() {
        IMetodoPagoDAO dao = new MetodoPagoDAOImpl();
        MetodoPago modelo = dao.search(testId);
        assertNotNull(modelo);
        assertEquals("Tarjeta Débito", modelo.getNombre());
        assertEquals("Pago con tarjeta de débito", modelo.getDescripcion());
        assertEquals("ACTIVO", modelo.getEstado());
    }

    @Test
    @Order(3)
    @Override
    public void shouldUpdateIfIdExists() {
        IMetodoPagoDAO dao = new MetodoPagoDAOImpl();
        MetodoPago modelo = new MetodoPago();
        modelo.setId(testId);
        modelo.setNombre("Tarjeta Crédito");
        modelo.setDescripcion("Pago con tarjeta de crédito");
        modelo.setEstado("INACTIVO");

        boolean updated = dao.update(modelo);
        assertTrue(updated);

        MetodoPago actualizado = dao.search(testId);
        assertEquals("Tarjeta Crédito", actualizado.getNombre());
        assertEquals("Pago con tarjeta de crédito", actualizado.getDescripcion());
        assertEquals("INACTIVO", actualizado.getEstado());
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IMetodoPagoDAO dao = new MetodoPagoDAOImpl();
        MetodoPago modelo = new MetodoPago();
        modelo.setId(idIncorrecto);
        modelo.setNombre("Transferencia");
        modelo.setDescripcion("Pago por transferencia");
        modelo.setEstado("ACTIVO");

        boolean updated = dao.update(modelo);
        assertFalse(updated);
    }

    @Test
    @Order(5)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IMetodoPagoDAO dao = new MetodoPagoDAOImpl();
        MetodoPago modelo = dao.search(idIncorrecto);
        assertNull(modelo);
    }

    @Test
    @Order(6)
    @Override
    public void shouldList() {
        IMetodoPagoDAO dao = new MetodoPagoDAOImpl();
        List<MetodoPago> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(7)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IMetodoPagoDAO dao = new MetodoPagoDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IMetodoPagoDAO dao = new MetodoPagoDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);

        MetodoPago modelo = dao.search(testId);
        assertNull(modelo);
    }
    
}

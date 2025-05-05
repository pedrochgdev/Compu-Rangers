package com.compurangers.platform.dao.mysql.financial;

import com.compurangers.platform.core.domain.financial.Impuesto;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.financial.IImpuestoDAO;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ImpuestoDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IImpuestoDAO dao = new ImpuestoDAOImpl();
        Impuesto modelo = new Impuesto();
        modelo.setNombre("Impuesto Prueba");
        modelo.setAbreviacion("IPR");
        modelo.setTipo("COMPRA");

        this.testId = dao.add(modelo);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldFindIfIdExists() {
        IImpuestoDAO dao = new ImpuestoDAOImpl();
        Impuesto modelo = dao.search(testId);
        assertNotNull(modelo);
        assertEquals("Impuesto Prueba", modelo.getNombre());
        assertEquals("IPR", modelo.getAbreviacion());
        assertEquals("COMPRA", modelo.getTipo());
    }

    @Test
    @Order(3)
    @Override
    public void shouldUpdateIfIdExists() {
        IImpuestoDAO dao = new ImpuestoDAOImpl();
        Impuesto modelo = new Impuesto();
        modelo.setId(testId);
        modelo.setNombre("Impuesto Actualizado");
        modelo.setAbreviacion("IA");
        modelo.setTipo("VENTA");

        boolean updated = dao.update(modelo);
        assertTrue(updated);

        Impuesto actualizado = dao.search(testId);
        assertEquals("Impuesto Actualizado", actualizado.getNombre());
        assertEquals("IA", actualizado.getAbreviacion());
        assertEquals("VENTA", actualizado.getTipo());
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IImpuestoDAO dao = new ImpuestoDAOImpl();
        Impuesto modelo = new Impuesto();
        modelo.setId(idIncorrecto);
        modelo.setNombre("Inexistente");
        modelo.setAbreviacion("XXX");
        modelo.setTipo("COMPRA");

        boolean updated = dao.update(modelo);
        assertFalse(updated);
    }

    @Test
    @Order(5)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IImpuestoDAO dao = new ImpuestoDAOImpl();
        Impuesto modelo = dao.search(idIncorrecto);
        assertNull(modelo);
    }

    @Test
    @Order(6)
    @Override
    public void shouldList() {
        IImpuestoDAO dao = new ImpuestoDAOImpl();
        List<Impuesto> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(7)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IImpuestoDAO dao = new ImpuestoDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IImpuestoDAO dao = new ImpuestoDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);

        Impuesto modelo = dao.search(testId);
        assertNull(modelo);
    }
    
}

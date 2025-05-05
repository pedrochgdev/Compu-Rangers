package com.compurangers.platform.dao.mysql.configuration;

import com.compurangers.platform.core.domain.configuration.MonedaPeriodo;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.configuration.IMonedaPeriodoDAO;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MonedaPeriodoDAOTest implements ICrudDAOTest {
    
    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IMonedaPeriodoDAO dao = new MonedaPeriodoDAOImpl();
        MonedaPeriodo modelo = new MonedaPeriodo();
        modelo.setMonedaId(1);
        modelo.setPeriodoId(2);
        modelo.setTipoCambio("COMPRA");
        modelo.setEstado("ACTIVO");
        modelo.setValor(3.785);

        this.testId = dao.add(modelo);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldFindIfIdExists() {
        IMonedaPeriodoDAO dao = new MonedaPeriodoDAOImpl();
        MonedaPeriodo modelo = dao.search(testId);
        assertNotNull(modelo);
        assertEquals("COMPRA", modelo.getTipoCambio());
        assertEquals("ACTIVO", modelo.getEstado());
        assertEquals(3.785, modelo.getValor(), 0.0001);
    }

    @Test
    @Order(3)
    @Override
    public void shouldUpdateIfIdExists() {
        IMonedaPeriodoDAO dao = new MonedaPeriodoDAOImpl();
        MonedaPeriodo modelo = new MonedaPeriodo();
        modelo.setId(testId);
        modelo.setTipoCambio("VENTA");
        modelo.setEstado("INACTIVO");
        modelo.setValor(3.925);
        
        boolean updated = dao.update(modelo);
        assertTrue(updated);

        MonedaPeriodo actualizado = dao.search(testId);
        assertEquals("VENTA", actualizado.getTipoCambio());
        assertEquals("INACTIVO", actualizado.getEstado());
        assertEquals(3.925, actualizado.getValor(), 0.0001);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IMonedaPeriodoDAO dao = new MonedaPeriodoDAOImpl();
        MonedaPeriodo modelo = new MonedaPeriodo();
        modelo.setId(idIncorrecto);
        modelo.setTipoCambio("VENTA");
        modelo.setEstado("ACTIVO");
        modelo.setValor(4.0);

        boolean updated = dao.update(modelo);
        assertFalse(updated);
    }

    @Test
    @Order(5)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IMonedaPeriodoDAO dao = new MonedaPeriodoDAOImpl();
        MonedaPeriodo modelo = dao.search(idIncorrecto);
        assertNull(modelo);
    }

    @Test
    @Order(6)
    @Override
    public void shouldList() {
        IMonedaPeriodoDAO dao = new MonedaPeriodoDAOImpl();
        List<MonedaPeriodo> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(7)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IMonedaPeriodoDAO dao = new MonedaPeriodoDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IMonedaPeriodoDAO dao = new MonedaPeriodoDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);

        MonedaPeriodo modelo = dao.search(testId);
        assertNull(modelo);
    }
    
}

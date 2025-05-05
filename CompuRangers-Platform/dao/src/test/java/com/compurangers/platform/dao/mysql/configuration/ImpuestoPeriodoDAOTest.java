package com.compurangers.platform.dao.mysql.configuration;

import com.compurangers.platform.core.domain.configuration.ImpuestoPeriodo;
import com.compurangers.platform.dao.configuration.IImpuestoPeriodoDAO;
import com.compurangers.platform.dao.ICrudDAOTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ImpuestoPeriodoDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IImpuestoPeriodoDAO impuestoPeriodoDao = new ImpuestoPeriodoDAOImpl();
        ImpuestoPeriodo impuestoPeriodo = new ImpuestoPeriodo();
        impuestoPeriodo.setPeriodoId(2);
        impuestoPeriodo.setImpuestoId(1);
        impuestoPeriodo.setTasa(18.0);
        impuestoPeriodo.setEstado("Activo");

        this.testId = impuestoPeriodoDao.add(impuestoPeriodo);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IImpuestoPeriodoDAO impuestoPeriodoDao = new ImpuestoPeriodoDAOImpl();
        ImpuestoPeriodo impuestoPeriodo = new ImpuestoPeriodo();
        impuestoPeriodo.setId(this.testId);
        impuestoPeriodo.setTasa(19.0);
        impuestoPeriodo.setEstado("Inactivo");

        boolean updated = impuestoPeriodoDao.update(impuestoPeriodo);
        assertTrue(updated);

        ImpuestoPeriodo updatedImpuestoPeriodo = impuestoPeriodoDao.search(this.testId);
        assertEquals(19.0, updatedImpuestoPeriodo.getTasa());
        assertEquals("INACTIVO", updatedImpuestoPeriodo.getEstado());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IImpuestoPeriodoDAO impuestoPeriodoDao = new ImpuestoPeriodoDAOImpl();
        ImpuestoPeriodo impuestoPeriodo = new ImpuestoPeriodo();
        impuestoPeriodo.setId(this.idIncorrecto);
        impuestoPeriodo.setTasa(20.0);
        impuestoPeriodo.setEstado("Activo");

        boolean updated = impuestoPeriodoDao.update(impuestoPeriodo);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IImpuestoPeriodoDAO impuestoPeriodoDao = new ImpuestoPeriodoDAOImpl();
        boolean deleted = impuestoPeriodoDao.delete(this.idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IImpuestoPeriodoDAO impuestoPeriodoDao = new ImpuestoPeriodoDAOImpl();
        ImpuestoPeriodo impuestoPeriodo = impuestoPeriodoDao.search(this.testId);
        assertNotNull(impuestoPeriodo);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IImpuestoPeriodoDAO impuestoPeriodoDao = new ImpuestoPeriodoDAOImpl();
        ImpuestoPeriodo impuestoPeriodo = impuestoPeriodoDao.search(this.idIncorrecto);
        assertNull(impuestoPeriodo);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IImpuestoPeriodoDAO impuestoPeriodoDao = new ImpuestoPeriodoDAOImpl();
        List<ImpuestoPeriodo> impuestoPeriodos = impuestoPeriodoDao.getAll();
        assertNotNull(impuestoPeriodos);
        assertFalse(impuestoPeriodos.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IImpuestoPeriodoDAO impuestoPeriodoDao = new ImpuestoPeriodoDAOImpl();
        boolean deleted = impuestoPeriodoDao.delete(this.testId);
        assertTrue(deleted);
    }
    
}

package com.compurangers.platform.dao.mysql.configuration;

import com.compurangers.platform.core.domain.configuration.Periodo;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.configuration.IPeriodoDAO;

import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PeriodoDAOTest implements ICrudDAOTest{

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IPeriodoDAO dao = new PeriodoDAOImpl();
        Periodo modelo = new Periodo();
        modelo.setFechaInicio(new Date(2025 - 1900, 4, 1));   // 1 de mayo 2025
        modelo.setFechaFin(new Date(2025 - 1900, 4, 31));     // 31 de mayo 2025

        this.testId = dao.add(modelo);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldFindIfIdExists() {
        IPeriodoDAO dao = new PeriodoDAOImpl();
        Periodo modelo = dao.search(testId);
        assertNotNull(modelo);
        assertEquals(2025 - 1900, modelo.getFechaInicio().getYear());
        assertEquals(4, modelo.getFechaInicio().getMonth());  // mayo
    }

    @Test
    @Order(3)
    @Override
    public void shouldUpdateIfIdExists() {
        IPeriodoDAO dao = new PeriodoDAOImpl();
        Periodo modelo = new Periodo();
        modelo.setId(testId);
        modelo.setFechaInicio(new Date(2025 - 1900, 5, 1)); // 1 de junio
        modelo.setFechaFin(new Date(2025 - 1900, 5, 30));   // 30 de junio

        boolean updated = dao.update(modelo);
        assertTrue(updated);

        Periodo actualizado = dao.search(testId);
        assertEquals(5, actualizado.getFechaInicio().getMonth()); // junio
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IPeriodoDAO dao = new PeriodoDAOImpl();
        Periodo modelo = new Periodo();
        modelo.setId(idIncorrecto);
        modelo.setFechaInicio(new Date(2025 - 1900, 6, 1));
        modelo.setFechaFin(new Date(2025 - 1900, 6, 31));

        boolean updated = dao.update(modelo);
        assertFalse(updated);
    }

    @Test
    @Order(5)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IPeriodoDAO dao = new PeriodoDAOImpl();
        Periodo modelo = dao.search(idIncorrecto);
        assertNull(modelo);
    }

    @Test
    @Order(6)
    @Override
    public void shouldList() {
        IPeriodoDAO dao = new PeriodoDAOImpl();
        List<Periodo> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(7)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IPeriodoDAO dao = new PeriodoDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IPeriodoDAO dao = new PeriodoDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);

        Periodo modelo = dao.search(testId);
        assertNull(modelo);
    }
    
}

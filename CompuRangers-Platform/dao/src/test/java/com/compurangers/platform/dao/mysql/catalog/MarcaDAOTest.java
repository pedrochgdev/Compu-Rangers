package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.dao.catalog.IMarcaDAO;
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
public class MarcaDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IMarcaDAO marcaDao = new MarcaDAOImpl();
        Marca marca = new Marca();
        marca.setNombre("Test Marca");

        this.testId = marcaDao.add(marca);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IMarcaDAO marcaDao = new MarcaDAOImpl();
        Marca marca = new Marca();
        marca.setId(this.testId);
        marca.setNombre("Updated Marca");

        boolean updated = marcaDao.update(marca);
        assertTrue(updated);

        Marca updatedMarca = marcaDao.search(this.testId);
        assertEquals("Updated Marca", updatedMarca.getNombre());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IMarcaDAO marcaDao = new MarcaDAOImpl();
        Marca marca = new Marca();
        marca.setId(this.idIncorrecto);
        marca.setNombre("Non-existent Marca");

        boolean updated = marcaDao.update(marca);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IMarcaDAO marcaDao = new MarcaDAOImpl();
        boolean deleted = marcaDao.delete(this.idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IMarcaDAO marcaDao = new MarcaDAOImpl();
        Marca marca = marcaDao.search(this.testId);
        assertNotNull(marca);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IMarcaDAO marcaDao = new MarcaDAOImpl();
        Marca marca = marcaDao.search(this.idIncorrecto);
        assertNull(marca);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IMarcaDAO marcaDao = new MarcaDAOImpl();
        List<Marca> marcas = marcaDao.getAll();
        assertNotNull(marcas);
        assertFalse(marcas.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IMarcaDAO marcaDao = new MarcaDAOImpl();
        boolean deleted = marcaDao.delete(this.testId);
        assertTrue(deleted);
    }
}

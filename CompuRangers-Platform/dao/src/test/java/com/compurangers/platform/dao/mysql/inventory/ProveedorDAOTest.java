package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Proveedor;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.inventory.IProveedorDAO;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProveedorDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IProveedorDAO dao = new ProveedorDAOImpl();

        Proveedor proveedor = new Proveedor();
        proveedor.setRazonSocial("Proveedor de Prueba");

        this.testId = dao.add(proveedor);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IProveedorDAO dao = new ProveedorDAOImpl();

        Proveedor proveedor = new Proveedor();
        proveedor.setId(testId);
        proveedor.setRazonSocial("Proveedor Actualizado");

        boolean actualizado = dao.update(proveedor);
        assertTrue(actualizado);

        Proveedor result = dao.search(testId);
        assertEquals("Proveedor Actualizado", result.getRazonSocial());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IProveedorDAO dao = new ProveedorDAOImpl();

        Proveedor proveedor = new Proveedor();
        proveedor.setId(idIncorrecto);
        proveedor.setRazonSocial("No existe");

        boolean actualizado = dao.update(proveedor);
        assertFalse(actualizado);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IProveedorDAO dao = new ProveedorDAOImpl();
        boolean eliminado = dao.delete(idIncorrecto);
        assertFalse(eliminado);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IProveedorDAO dao = new ProveedorDAOImpl();
        Proveedor proveedor = dao.search(testId);
        assertNotNull(proveedor);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IProveedorDAO dao = new ProveedorDAOImpl();
        Proveedor proveedor = dao.search(idIncorrecto);
        assertNull(proveedor);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IProveedorDAO dao = new ProveedorDAOImpl();
        List<Proveedor> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IProveedorDAO dao = new ProveedorDAOImpl();
        boolean eliminado = dao.delete(testId);
        assertTrue(eliminado);
    }
    
}

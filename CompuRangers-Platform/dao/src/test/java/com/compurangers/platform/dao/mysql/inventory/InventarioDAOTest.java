package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.inventory.IInventarioDAO;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InventarioDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    private final int loteId = 1;
    private final int productoId = 1;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IInventarioDAO dao = new InventarioDAOImpl();
        Producto producto = new ProductoDAOImpl().search(productoId);

        Inventario inventario = new Inventario();
        inventario.setCantidadDisponible(50);
        inventario.setLoteId(loteId);
        inventario.setProducto(producto);

        this.testId = dao.add(inventario);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IInventarioDAO dao = new InventarioDAOImpl();
        Producto producto = new ProductoDAOImpl().search(productoId);

        Inventario inventario = new Inventario();
        inventario.setId(testId);
        inventario.setCantidadDisponible(80);
        inventario.setLoteId(loteId);
        inventario.setProducto(producto);

        boolean updated = dao.update(inventario);
        assertTrue(updated);

        Inventario actualizado = dao.search(testId);
        assertEquals(80, actualizado.getCantidadDisponible());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IInventarioDAO dao = new InventarioDAOImpl();
        Producto producto = new ProductoDAOImpl().search(productoId);

        Inventario inventario = new Inventario();
        inventario.setId(idIncorrecto);
        inventario.setCantidadDisponible(25);
        inventario.setLoteId(loteId);
        inventario.setProducto(producto);

        boolean updated = dao.update(inventario);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IInventarioDAO dao = new InventarioDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IInventarioDAO dao = new InventarioDAOImpl();
        Inventario inventario = dao.search(testId);
        assertNotNull(inventario);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IInventarioDAO dao = new InventarioDAOImpl();
        Inventario inventario = dao.search(idIncorrecto);
        assertNull(inventario);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IInventarioDAO dao = new InventarioDAOImpl();
        List<Inventario> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IInventarioDAO dao = new InventarioDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }
    
}

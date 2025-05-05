package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.sales.IItemCarritoDAO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemCarritoDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;
    private final int productoId = 1;
    private final int carritoId = 1; 

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        ItemCarrito item = new ItemCarrito();
        item.setProducto(new Producto());
        item.getProducto().setId(productoId);
        item.setCantidad(2);
        item.setSubtotal(50.0);
        item.setCarritoId(carritoId);

        this.testId = dao.add(item);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        ItemCarrito item = new ItemCarrito();
        item.setId(testId);
        item.setProducto(new Producto());
        item.getProducto().setId(productoId);
        item.setCantidad(3);
        item.setSubtotal(75.0);
        item.setCarritoId(carritoId);

        boolean updated = dao.update(item);
        assertTrue(updated);

        ItemCarrito actualizado = dao.search(testId);
        assertEquals(3, actualizado.getCantidad());
        assertEquals(75.0, actualizado.getSubtotal());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        ItemCarrito item = new ItemCarrito();
        item.setId(idIncorrecto);
        item.setProducto(new Producto());
        item.getProducto().setId(productoId);
        item.setCantidad(1);
        item.setSubtotal(25.0);
        item.setCarritoId(carritoId);

        boolean updated = dao.update(item);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        ItemCarrito item = dao.search(testId);
        assertNotNull(item);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        ItemCarrito item = dao.search(idIncorrecto);
        assertNull(item);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        List<ItemCarrito> items = dao.getAll();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldGetAllByCarritoId() {
        IItemCarritoDAO dao = new ItemCarritoDAOImpl();
        List<ItemCarrito> items = dao.getAllByForeignKey(carritoId);
        assertNotNull(items);
    }
    
}

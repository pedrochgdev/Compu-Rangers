package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.sales.DetalleVenta;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.sales.IDetalleVentaDAO;
import com.compurangers.platform.dao.catalog.IProductoDAO;
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
public class DetalleVentaDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;
    private final int ordenVentaId = 1;
    private final int productoId = 1;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        DetalleVenta detalle = new DetalleVenta();
        detalle.setCantidad(2);
        detalle.setSubtotal(100.00);
        detalle.setDevuelto(0);
        detalle.setIdOrdenVenta(ordenVentaId);

        IProductoDAO productoDAO = new ProductoDAOImpl();
        Producto producto = productoDAO.search(productoId);
        assertNotNull(producto); // Validamos que el producto exista
        detalle.setProducto(producto);

        this.testId = dao.add(detalle);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        DetalleVenta detalle = new DetalleVenta();
        detalle.setId(testId);
        detalle.setCantidad(3);
        detalle.setSubtotal(150.00);
        detalle.setDevuelto(1);
        detalle.setIdOrdenVenta(ordenVentaId);

        IProductoDAO productoDAO = new ProductoDAOImpl();
        Producto producto = productoDAO.search(productoId);
        detalle.setProducto(producto);

        boolean updated = dao.update(detalle);
        assertTrue(updated);

        DetalleVenta actualizado = dao.search(testId);
        assertEquals(3, actualizado.getCantidad());
        assertEquals(150.00, actualizado.getSubtotal());
        assertEquals(1, actualizado.getDevuelto());
        assertEquals(productoId, actualizado.getProducto().getId());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        DetalleVenta detalle = new DetalleVenta();
        detalle.setId(idIncorrecto);
        detalle.setCantidad(1);
        detalle.setSubtotal(50.00);
        detalle.setDevuelto(0);
        detalle.setIdOrdenVenta(ordenVentaId);

        IProductoDAO productoDAO = new ProductoDAOImpl();
        Producto producto = productoDAO.search(productoId);
        detalle.setProducto(producto);

        boolean updated = dao.update(detalle);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        DetalleVenta detalle = dao.search(testId);
        assertNotNull(detalle);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        DetalleVenta detalle = dao.search(idIncorrecto);
        assertNull(detalle);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        List<DetalleVenta> detalles = dao.getAll();
        assertNotNull(detalles);
        assertFalse(detalles.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldGetAllFromOrdenVentaId() {
        IDetalleVentaDAO dao = new DetalleVentaDAOImpl();
        List<DetalleVenta> detalles = dao.getAllByForeignKey(ordenVentaId);
        assertNotNull(detalles);
    }
    
}

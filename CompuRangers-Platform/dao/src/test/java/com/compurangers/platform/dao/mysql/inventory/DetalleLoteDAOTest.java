package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.inventory.DetalleLote;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.inventory.IDetalleLoteDAO;
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
public class DetalleLoteDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;
    private final int loteId = 1;

    private Producto getProductoValido() {
        IProductoDAO productoDAO = new ProductoDAOImpl();
        List<Producto> productos = productoDAO.getAll();
        assertFalse(productos.isEmpty());
        return productos.get(0);
    }

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        DetalleLote detalle = new DetalleLote();
        detalle.setCantidad(10);
        detalle.setPrecioCompra(20.5);
        detalle.setLoteId(loteId);
        detalle.setProducto(getProductoValido());

        this.testId = dao.add(detalle);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        DetalleLote detalle = new DetalleLote();
        detalle.setId(testId);
        detalle.setCantidad(15);
        detalle.setPrecioCompra(30.0);
        detalle.setLoteId(loteId);
        detalle.setProducto(getProductoValido());

        boolean updated = dao.update(detalle);
        assertTrue(updated);

        DetalleLote actualizado = dao.search(testId);
        assertEquals(15, actualizado.getCantidad());
        assertEquals(30.0, actualizado.getPrecioCompra());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        DetalleLote detalle = new DetalleLote();
        detalle.setId(idIncorrecto);
        detalle.setCantidad(5);
        detalle.setPrecioCompra(10.0);
        detalle.setLoteId(loteId);
        detalle.setProducto(getProductoValido());

        boolean updated = dao.update(detalle);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        DetalleLote detalle = dao.search(testId);
        assertNotNull(detalle);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        DetalleLote detalle = dao.search(idIncorrecto);
        assertNull(detalle);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        List<DetalleLote> detalles = dao.getAll();
        assertNotNull(detalles);
        assertFalse(detalles.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldGetAllFromLoteId() {
        IDetalleLoteDAO dao = new DetalleLoteDAOImpl();
        List<DetalleLote> detalles = dao.getAllByForeignKey(loteId);
        assertNotNull(detalles);
    }
    
}

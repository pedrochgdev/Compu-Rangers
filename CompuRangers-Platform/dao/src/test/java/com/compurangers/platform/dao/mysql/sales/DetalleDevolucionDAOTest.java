package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.DetalleDevolucion;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.sales.IDetalleDevolucionDAO;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DetalleDevolucionDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;
    private final int ordenDevolucionId = 1;
    private final int detalleVentaId = 1;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        DetalleDevolucion detalle = new DetalleDevolucion();
        detalle.setCantidad(3);
        detalle.setDetalleVentaId(detalleVentaId);
        detalle.setOrdenDevolucionId(ordenDevolucionId);

        this.testId = dao.add(detalle);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        DetalleDevolucion detalle = new DetalleDevolucion();
        detalle.setId(testId);
        detalle.setCantidad(5);
        detalle.setDetalleVentaId(detalleVentaId);
        detalle.setOrdenDevolucionId(ordenDevolucionId);

        boolean updated = dao.update(detalle);
        assertTrue(updated);

        DetalleDevolucion actualizado = dao.search(testId);
        assertEquals(5, actualizado.getCantidad());
        assertEquals(detalleVentaId, actualizado.getDetalleVentaId());
        assertEquals(ordenDevolucionId, actualizado.getOrdenDevolucionId());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        DetalleDevolucion detalle = new DetalleDevolucion();
        detalle.setId(idIncorrecto);
        detalle.setCantidad(1);
        detalle.setDetalleVentaId(detalleVentaId);
        detalle.setOrdenDevolucionId(ordenDevolucionId);

        boolean updated = dao.update(detalle);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        DetalleDevolucion detalle = dao.search(testId);
        assertNotNull(detalle);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        DetalleDevolucion detalle = dao.search(idIncorrecto);
        assertNull(detalle);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        List<DetalleDevolucion> detalles = dao.getAll();
        assertNotNull(detalles);
        assertFalse(detalles.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldGetAllFromOrdenDevolucionId() {
        IDetalleDevolucionDAO dao = new DetalleDevolucionDAOImpl();
        List<DetalleDevolucion> detalles = dao.getAllByForeignKey(ordenDevolucionId);
        assertNotNull(detalles);
    }
    
}

package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.sales.ICarritoDAO;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarritoDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    private final int usuarioIdExistente = 2; 

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        ICarritoDAO dao = new CarritoDAOImpl();

        Carrito carrito = new Carrito();
        carrito.setTotal(150.0);
        carrito.setCantidadProductos(3);
        carrito.setUsuarioId(usuarioIdExistente);

        this.testId = dao.add(carrito);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        ICarritoDAO dao = new CarritoDAOImpl();

        Carrito carrito = new Carrito();
        carrito.setId(testId);
        carrito.setTotal(200.0);
        carrito.setCantidadProductos(5);
        carrito.setUsuarioId(usuarioIdExistente);

        boolean actualizado = dao.update(carrito);
        assertTrue(actualizado);

        Carrito result = dao.search(testId);
        assertEquals(200.0, result.getTotal());
        assertEquals(5, result.getCantidadProductos());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        ICarritoDAO dao = new CarritoDAOImpl();

        Carrito carrito = new Carrito();
        carrito.setId(idIncorrecto);
        carrito.setTotal(300.0);
        carrito.setCantidadProductos(2);
        carrito.setUsuarioId(usuarioIdExistente);

        boolean actualizado = dao.update(carrito);
        assertFalse(actualizado);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        ICarritoDAO dao = new CarritoDAOImpl();
        boolean eliminado = dao.delete(idIncorrecto);
        assertFalse(eliminado);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        ICarritoDAO dao = new CarritoDAOImpl();
        Carrito carrito = dao.search(testId);
        assertNotNull(carrito);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        ICarritoDAO dao = new CarritoDAOImpl();
        Carrito carrito = dao.search(idIncorrecto);
        assertNull(carrito);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        ICarritoDAO dao = new CarritoDAOImpl();
        List<Carrito> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        ICarritoDAO dao = new CarritoDAOImpl();
        boolean eliminado = dao.delete(testId);
        assertTrue(eliminado);
    }
    
}

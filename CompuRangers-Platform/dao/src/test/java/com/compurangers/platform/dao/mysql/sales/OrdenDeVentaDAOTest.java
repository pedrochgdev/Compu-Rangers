package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.sales.IOrdenDeVentaDAO;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrdenDeVentaDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IOrdenDeVentaDAO dao = new OrdenDeVentaDAOImpl();
        OrdenDeVenta venta = new OrdenDeVenta();
        venta.setEstado("PAGADO");
        venta.setFecha(new Date());
        venta.setTotal(200.50);
        venta.setClienteId(2);
        venta.setDireccion("Av. Siempre Viva 123");

        this.testId = dao.add(venta);
        assertTrue(testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IOrdenDeVentaDAO dao = new OrdenDeVentaDAOImpl();
        OrdenDeVenta venta = new OrdenDeVenta();
        venta.setId(testId);
        venta.setEstado("ENTREGADO");
        venta.setFecha(new Date());
        venta.setTotal(250.75);
        venta.setClienteId(2);
        venta.setDireccion("Calle Falsa 456");

        boolean updated = dao.update(venta);
        assertTrue(updated);

        OrdenDeVenta actualizado = dao.search(testId);
        assertEquals("ENTREGADO", actualizado.getEstado());
        assertEquals("Calle Falsa 456", actualizado.getDireccion());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IOrdenDeVentaDAO dao = new OrdenDeVentaDAOImpl();
        OrdenDeVenta venta = new OrdenDeVenta();
        venta.setId(idIncorrecto);
        venta.setEstado("ENVIADO");
        venta.setFecha(new Date());
        venta.setTotal(100.00);
        venta.setClienteId(2);
        venta.setDireccion("Dirección falsa");

        boolean updated = dao.update(venta);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IOrdenDeVentaDAO dao = new OrdenDeVentaDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IOrdenDeVentaDAO dao = new OrdenDeVentaDAOImpl();
        OrdenDeVenta found = dao.search(testId);
        assertNotNull(found);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IOrdenDeVentaDAO dao = new OrdenDeVentaDAOImpl();
        OrdenDeVenta found = dao.search(idIncorrecto);
        assertNull(found);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IOrdenDeVentaDAO dao = new OrdenDeVentaDAOImpl();
        List<OrdenDeVenta> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IOrdenDeVentaDAO dao = new OrdenDeVentaDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }
    
}

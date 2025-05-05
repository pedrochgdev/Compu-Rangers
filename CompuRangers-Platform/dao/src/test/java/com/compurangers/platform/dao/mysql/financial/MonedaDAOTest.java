package com.compurangers.platform.dao.mysql.financial;

import com.compurangers.platform.core.domain.financial.Moneda;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.financial.IMonedaDAO;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MonedaDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IMonedaDAO dao = new MonedaDAOImpl();
        Moneda moneda = new Moneda();
        moneda.setCodigo("USD");
        moneda.setNombre("Dólar estadounidense");

        this.testId = dao.add(moneda);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldFindIfIdExists() {
        IMonedaDAO dao = new MonedaDAOImpl();
        Moneda moneda = dao.search(testId);
        assertNotNull(moneda);
        assertEquals("USD", moneda.getCodigo());
        assertEquals("Dólar estadounidense", moneda.getNombre());
    }

    @Test
    @Order(3)
    @Override
    public void shouldUpdateIfIdExists() {
        IMonedaDAO dao = new MonedaDAOImpl();
        Moneda moneda = new Moneda();
        moneda.setId(testId);
        moneda.setCodigo("EUR");
        moneda.setNombre("Euro");

        boolean updated = dao.update(moneda);
        assertTrue(updated);

        Moneda actualizada = dao.search(testId);
        assertEquals("EUR", actualizada.getCodigo());
        assertEquals("Euro", actualizada.getNombre());
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IMonedaDAO dao = new MonedaDAOImpl();
        Moneda moneda = new Moneda();
        moneda.setId(idIncorrecto);
        moneda.setCodigo("JPY");
        moneda.setNombre("Yen japonés");

        boolean updated = dao.update(moneda);
        assertFalse(updated);
    }

    @Test
    @Order(5)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IMonedaDAO dao = new MonedaDAOImpl();
        Moneda moneda = dao.search(idIncorrecto);
        assertNull(moneda);
    }

    @Test
    @Order(6)
    @Override
    public void shouldList() {
        IMonedaDAO dao = new MonedaDAOImpl();
        List<Moneda> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(7)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IMonedaDAO dao = new MonedaDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IMonedaDAO dao = new MonedaDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);

        Moneda moneda = dao.search(testId);
        assertNull(moneda);
    }
    
}

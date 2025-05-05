package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Lote;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.inventory.ILoteDAO;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoteDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;
    private final int documentoCompraNo = 2001;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        ILoteDAO dao = new LoteDAOImpl();

        Lote lote = new Lote();
        lote.setFechaCreacion(new Date());
        lote.setEstado("ABIERTO");
        lote.setDocumentoCompraNo(documentoCompraNo);

        this.testId = dao.add(lote);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        ILoteDAO dao = new LoteDAOImpl();

        Lote lote = new Lote();
        lote.setId(testId);
        lote.setFechaCreacion(new Date());
        lote.setEstado("CERRADO");
        lote.setDocumentoCompraNo(documentoCompraNo);

        boolean actualizado = dao.update(lote);
        assertTrue(actualizado);

        Lote result = dao.search(testId);
        assertEquals("CERRADO", result.getEstado());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        ILoteDAO dao = new LoteDAOImpl();

        Lote lote = new Lote();
        lote.setId(idIncorrecto);
        lote.setFechaCreacion(new Date());
        lote.setEstado("CANCELADO");
        lote.setDocumentoCompraNo(documentoCompraNo);

        boolean actualizado = dao.update(lote);
        assertFalse(actualizado);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        ILoteDAO dao = new LoteDAOImpl();
        boolean eliminado = dao.delete(idIncorrecto);
        assertFalse(eliminado);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        ILoteDAO dao = new LoteDAOImpl();
        Lote lote = dao.search(testId);
        assertNotNull(lote);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        ILoteDAO dao = new LoteDAOImpl();
        Lote lote = dao.search(idIncorrecto);
        assertNull(lote);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        ILoteDAO dao = new LoteDAOImpl();
        List<Lote> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        ILoteDAO dao = new LoteDAOImpl();
        boolean eliminado = dao.delete(testId);
        assertTrue(eliminado);
    }
    
}

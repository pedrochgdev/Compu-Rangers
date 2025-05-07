package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.DocumentoDeVentas;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.sales.IDocumentoDeVentasDAO;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DocumentoDeVentasDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;
    private final int ordenVentaId = 1;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        DocumentoDeVentas doc = new DocumentoDeVentas();
        doc.setSubtotal(150.0);
        doc.setImpuestos(27.0);
        doc.setTotal(177.0);
        doc.setOrdenDeVentaId(ordenVentaId);

        this.testId = dao.add(doc);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        DocumentoDeVentas doc = new DocumentoDeVentas();
        doc.setNumero(testId);
        doc.setSubtotal(300.0);
        doc.setImpuestos(54.0);
        doc.setTotal(354.0);
        doc.setOrdenDeVentaId(ordenVentaId);

        boolean updated = dao.update(doc);
        assertTrue(updated);

        DocumentoDeVentas actualizado = dao.search(testId);
        assertEquals(300.0, actualizado.getSubtotal());
        assertEquals(54.0, actualizado.getImpuestos());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        DocumentoDeVentas doc = new DocumentoDeVentas();
        doc.setNumero(idIncorrecto);
        doc.setSubtotal(100.0);
        doc.setImpuestos(18.0);
        doc.setTotal(118.0);
        doc.setOrdenDeVentaId(ordenVentaId);

        boolean updated = dao.update(doc);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        DocumentoDeVentas doc = dao.search(testId);
        assertNotNull(doc);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        DocumentoDeVentas doc = dao.search(idIncorrecto);
        assertNull(doc);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        List<DocumentoDeVentas> documentos = dao.getAll();
        assertNotNull(documentos);
        assertFalse(documentos.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldGetAllByOrdenVentaId() {
        IDocumentoDeVentasDAO dao = new DocumentoDeVentasDAOImpl();
        List<DocumentoDeVentas> docs = dao.getAllByForeignKey(ordenVentaId);
        assertNotNull(docs);
    }
    
}

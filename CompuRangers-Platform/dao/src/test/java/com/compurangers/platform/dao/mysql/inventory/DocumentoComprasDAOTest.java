package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.DocumentoCompras;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.inventory.IDocumentoComprasDAO;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DocumentoComprasDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;
    private final int proveedorId = 1;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        DocumentoCompras doc = new DocumentoCompras();
        doc.setSubtotal(100.0);
        doc.setImpuestos(18.0);
        doc.setTotal(118.0);
        doc.setProveedorId(proveedorId);

        this.testId = dao.add(doc);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        DocumentoCompras doc = new DocumentoCompras();
        doc.setId(testId);
        doc.setNumero(2002);
        doc.setSubtotal(200.0);
        doc.setImpuestos(36.0);
        doc.setTotal(236.0);
        doc.setProveedorId(proveedorId);

        boolean updated = dao.update(doc);
        assertTrue(updated);

        DocumentoCompras actualizado = dao.search(2002);
        assertEquals(200.0, actualizado.getSubtotal());
        assertEquals(36.0, actualizado.getImpuestos());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        DocumentoCompras doc = new DocumentoCompras();
        doc.setId(idIncorrecto);
        doc.setNumero(idIncorrecto);
        doc.setSubtotal(50.0);
        doc.setImpuestos(9.0);
        doc.setTotal(59.0);
        doc.setProveedorId(proveedorId);

        boolean updated = dao.update(doc);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        DocumentoCompras doc = dao.search(2001);
        assertNotNull(doc);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        DocumentoCompras doc = dao.search(idIncorrecto);
        assertNull(doc);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        List<DocumentoCompras> documentos = dao.getAll();
        assertNotNull(documentos);
        assertFalse(documentos.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        boolean deleted = dao.delete(2002);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldGetAllByProveedorId() {
        IDocumentoComprasDAO dao = new DocumentoComprasDAOImpl();
        List<DocumentoCompras> docs = dao.getAllByForeignKey(proveedorId);
        assertNotNull(docs);
    }
    
}
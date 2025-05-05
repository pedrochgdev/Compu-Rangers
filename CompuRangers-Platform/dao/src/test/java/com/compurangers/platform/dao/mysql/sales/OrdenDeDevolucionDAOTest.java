package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeDevolucion;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.sales.IOrdenDeDevolucionDAO;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrdenDeDevolucionDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;
    private final int docVentaNumero = 1001;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        OrdenDeDevolucion devolucion = new OrdenDeDevolucion();
        devolucion.setMotivo("Producto defectuoso");
        devolucion.setFechaRegistro(new Date());
        devolucion.setTipoDevolucion("REEMBOLSO");
        devolucion.setDocumentoDeVentasNumero(docVentaNumero);

        this.testId = dao.add(devolucion);
        assertTrue(testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        OrdenDeDevolucion devolucion = new OrdenDeDevolucion();
        devolucion.setId(testId);
        devolucion.setMotivo("Cambio por otro modelo");
        devolucion.setFechaRegistro(new Date());
        devolucion.setTipoDevolucion("GIFTCARD");
        devolucion.setDocumentoDeVentasNumero(docVentaNumero);

        boolean updated = dao.update(devolucion);
        assertTrue(updated);

        OrdenDeDevolucion actualizado = dao.search(testId);
        assertEquals("Cambio por otro modelo", actualizado.getMotivo());
        assertEquals("GIFTCARD", actualizado.getTipoDevolucion());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        OrdenDeDevolucion devolucion = new OrdenDeDevolucion();
        devolucion.setId(idIncorrecto);
        devolucion.setMotivo("Motivo inexistente");
        devolucion.setFechaRegistro(new Date());
        devolucion.setTipoDevolucion("GIFTCARD");
        devolucion.setDocumentoDeVentasNumero(docVentaNumero);

        boolean updated = dao.update(devolucion);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        OrdenDeDevolucion found = dao.search(testId);
        assertNotNull(found);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        OrdenDeDevolucion found = dao.search(idIncorrecto);
        assertNull(found);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        List<OrdenDeDevolucion> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldListAllFromDocVenta() {
        IOrdenDeDevolucionDAO dao = new OrdenDeDevolucionDAOImpl();
        List<OrdenDeDevolucion> lista = dao.getAllByForeignKey(docVentaNumero);
        assertNotNull(lista);
    }
    
}

package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Log;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.user.ILogDAO;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 999999;

    private final int usuarioIdPrueba = 1;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        ILogDAO dao = new LogDAOImpl();
        Log log = new Log();
        log.setAccion("Prueba de inserción");
        log.setFecha(new Date());
        log.setUsuarioId(usuarioIdPrueba);

        testId = dao.add(log);
        assertTrue(testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        ILogDAO dao = new LogDAOImpl();
        Log log = new Log();
        log.setId(testId);
        log.setAccion("Acción actualizada");
        log.setFecha(new Date());
        log.setUsuarioId(usuarioIdPrueba);

        boolean updated = dao.update(log);
        assertTrue(updated);

        Log actualizado = dao.search(testId);
        assertEquals("Acción actualizada", actualizado.getAccion());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        ILogDAO dao = new LogDAOImpl();
        Log log = new Log();
        log.setId(idIncorrecto);
        log.setAccion("Intento fallido");
        log.setFecha(new Date());
        log.setUsuarioId(usuarioIdPrueba);

        boolean updated = dao.update(log);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        ILogDAO dao = new LogDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        ILogDAO dao = new LogDAOImpl();
        Log log = dao.search(testId);
        assertNotNull(log);
        assertEquals(testId, log.getId());
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        ILogDAO dao = new LogDAOImpl();
        Log log = dao.search(idIncorrecto);
        assertNull(log);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        ILogDAO dao = new LogDAOImpl();
        List<Log> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        ILogDAO dao = new LogDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldListByUsuarioId() {
        ILogDAO dao = new LogDAOImpl();
        List<Log> logs = dao.getAllByForeignKey(usuarioIdPrueba);
        assertNotNull(logs);
    }
}

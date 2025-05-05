package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Admin;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.user.IAdminDAO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IAdminDAO dao = new AdminDAOImpl();
        Admin admin = new Admin();
        admin.setUsername("admin.test");
        admin.setNombreCompleto("Admin de Prueba");
        admin.setTelefono("987654321");
        admin.setCorreoElectronico("admin@correo.com");
        admin.setDireccion("Av. Admin #123");
        admin.setContrasena("adminPass123");
        admin.setFechaIngreso(new java.util.Date());

        this.testId = dao.add(admin);
        
        assertTrue(testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IAdminDAO dao = new AdminDAOImpl();
        Admin admin = new Admin();
        admin.setId(testId);
        admin.setUsername("admin.actualizado");
        admin.setNombreCompleto("Admin Actualizado");
        admin.setTelefono("912345678");
        admin.setCorreoElectronico("actualizado@correo.com");
        admin.setDireccion("Av. Nueva Admin #456");
        admin.setContrasena("nuevoPass456");
        admin.setFechaIngreso(new java.util.Date()); // Actualiza la fecha de ingreso

        boolean updated = dao.update(admin);
        assertTrue(updated);

        Admin actualizado = (Admin) dao.search(testId);
        assertEquals("Av. Nueva Admin #456", actualizado.getDireccion());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IAdminDAO dao = new AdminDAOImpl();
        Admin admin = new Admin();
        admin.setId(idIncorrecto);
        admin.setDireccion("Falsa Direccion");

        boolean updated = dao.update(admin);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IAdminDAO dao = new AdminDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IAdminDAO dao = new AdminDAOImpl();
        Usuario admin = dao.search(testId);
        assertNotNull(admin);
        assertEquals(testId, admin.getId());
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IAdminDAO dao = new AdminDAOImpl();
        Usuario admin = dao.search(idIncorrecto);
        assertNull(admin);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IAdminDAO dao = new AdminDAOImpl();
        List<Usuario> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IAdminDAO dao = new AdminDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }
    
    @Test
    @Order(9)
    public void shouldFindUserIdByUsername() {
        IAdminDAO dao = new AdminDAOImpl();

        int userId = dao.getUserByField("username", "admin1");

        assertTrue(userId > 0);
        assertEquals(1, userId);
    }
    
    @Test
    @Order(10)
    public void shouldFindUserIdByEmail() {
        IAdminDAO dao = new AdminDAOImpl();

        int userId = dao.getUserByField("email", "admin@example.com");

        assertTrue(userId > 0);
        assertEquals(1, userId);
    }

    @Test
    @Order(11)
    public void shouldReturnPasswordHash() {
        IAdminDAO dao = new AdminDAOImpl();

        String hash = dao.getPasswordHash(1);

        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }
    
}

package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.user.IClienteDAO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteDAOTest implements ICrudDAOTest {

    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IClienteDAO dao = new ClienteDAOImpl();
        Cliente cliente = new Cliente();
        cliente.setUsername("cliente.test");
        cliente.setNombreCompleto("Cliente de Prueba");
        cliente.setTelefono("123456789");
        cliente.setCorreoElectronico("cliente@correo.com");
        cliente.setDireccion("Av. Test #123");
        cliente.setAdmin(false);
        cliente.setContrasena("password123");
        cliente.setDireccionPreferida("Calle Siempre Viva 742");

        this.testId = dao.add(cliente);
        
        assertTrue(testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IClienteDAO dao = new ClienteDAOImpl();
        Cliente cliente = new Cliente();
        cliente.setId(testId);
        cliente.setUsername("cliente.actualizado");
        cliente.setNombreCompleto("Cliente Actualizado");
        cliente.setTelefono("987654321");
        cliente.setCorreoElectronico("actualizado@correo.com");
        cliente.setDireccion("Av. Nueva #456");
        cliente.setAdmin(false);
        cliente.setContrasena("nuevaPass456");
        cliente.setDireccionPreferida("Calle Renovada 101");

        boolean updated = dao.update(cliente);
        assertTrue(updated);

        Cliente actualizado = (Cliente) dao.search(testId);
        assertEquals("Calle Renovada 101", actualizado.getDireccionPreferida());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IClienteDAO dao = new ClienteDAOImpl();
        Cliente cliente = new Cliente();
        cliente.setId(idIncorrecto);
        cliente.setDireccionPreferida("Falsa Direccion");

        boolean updated = dao.update(cliente);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IClienteDAO dao = new ClienteDAOImpl();
        boolean deleted = dao.delete(idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IClienteDAO dao = new ClienteDAOImpl();
        Usuario cliente = dao.search(testId);
        assertNotNull(cliente);
        assertEquals(testId, cliente.getId());
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IClienteDAO dao = new ClienteDAOImpl();
        Usuario cliente = dao.search(idIncorrecto);
        assertNull(cliente);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IClienteDAO dao = new ClienteDAOImpl();
        List<Usuario> lista = dao.getAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IClienteDAO dao = new ClienteDAOImpl();
        boolean deleted = dao.delete(testId);
        assertTrue(deleted);
    }
    
    @Test
    @Order(9)
    public void shouldFindUserIdByUsername() {
        IClienteDAO dao = new ClienteDAOImpl();

        int userId = dao.getUserByField("username", "client1");

        assertTrue(userId > 0);
        assertEquals(2, userId);
    }
    
    @Test
    @Order(10)
    public void shouldFindUserIdByEmail() {
        IClienteDAO dao = new ClienteDAOImpl();

        int userId = dao.getUserByField("email", "client@example.com");

        assertTrue(userId > 0);
        assertEquals(2, userId);
    }

    @Test
    @Order(11)
    public void shouldReturnPasswordHash() {
        IClienteDAO dao = new ClienteDAOImpl();

        String hash = dao.getPasswordHash(2);

        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }
    
}

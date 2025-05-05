package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.dao.catalog.IProductoDAO;
import com.compurangers.platform.dao.ICrudDAOTest;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductoDAOTest implements ICrudDAOTest {
    private int testId;
    private final int idIncorrecto = 99999;

    private final int categoriaId = 1;
    private final int marcaId = 1;

    private Producto crearProductoTest() {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);

        Marca marca = new Marca();
        marca.setId(marcaId);

        Producto producto = new Producto();
        producto.setSku("TESTSKU001");
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción de prueba");
        producto.setPrecioVenta(199.99);
        producto.setCategoria(categoria);
        producto.setMarca(marca);

        return producto;
    }

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        IProductoDAO productoDao = new ProductoDAOImpl();
        Producto producto = crearProductoTest();

        this.testId = productoDao.add(producto);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        IProductoDAO productoDao = new ProductoDAOImpl();
        Producto producto = crearProductoTest();
        producto.setId(this.testId);
        producto.setNombre("Producto Actualizado");

        boolean updated = productoDao.update(producto);
        assertTrue(updated);

        Producto actualizado = productoDao.search(this.testId);
        assertEquals("Producto Actualizado", actualizado.getNombre());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        IProductoDAO productoDao = new ProductoDAOImpl();
        Producto producto = crearProductoTest();
        producto.setId(this.idIncorrecto);
        producto.setNombre("Producto Fantasma");

        boolean updated = productoDao.update(producto);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        IProductoDAO productoDao = new ProductoDAOImpl();
        boolean deleted = productoDao.delete(this.idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        IProductoDAO productoDao = new ProductoDAOImpl();
        Producto producto = productoDao.search(this.testId);
        assertNotNull(producto);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        IProductoDAO productoDao = new ProductoDAOImpl();
        Producto producto = productoDao.search(this.idIncorrecto);
        assertNull(producto);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        IProductoDAO productoDao = new ProductoDAOImpl();
        List<Producto> productos = productoDao.getAll();
        assertNotNull(productos);
        assertFalse(productos.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        IProductoDAO productoDao = new ProductoDAOImpl();
        boolean deleted = productoDao.delete(this.testId);
        assertTrue(deleted);
    }
}

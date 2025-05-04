package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.ICrudDAOTest;
import com.compurangers.platform.dao.catalog.ICategoriaDAO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaDAOTest implements ICrudDAOTest {
    private int testId;
    private final int idIncorrecto = 99999;

    @Test
    @Order(1)
    @Override
    public void shouldInsert() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        Categoria categoria = new Categoria();
        categoria.setNombre("Test Category");
        categoria.setCategoriaPadre(null);

        this.testId = categoriaDao.add(categoria);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void shouldUpdateIfIdExists() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        Categoria categoria = new Categoria();
        categoria.setId(this.testId);
        categoria.setNombre("Updated Test Category");
        categoria.setCategoriaPadre(null);

        boolean updated = categoriaDao.update(categoria);
        assertTrue(updated);

        Categoria updatedCategoria = categoriaDao.search(this.testId);
        assertEquals("Updated Test Category", updatedCategoria.getNombre());
    }

    @Test
    @Order(3)
    @Override
    public void shouldNotUpdateIfIdDoesNotExist() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        Categoria categoria = new Categoria();
        categoria.setId(this.idIncorrecto);
        categoria.setNombre("Non-existent Category");
        categoria.setCategoriaPadre(null);

        boolean updated = categoriaDao.update(categoria);
        assertFalse(updated);
    }

    @Test
    @Order(4)
    @Override
    public void shouldNotDeleteIfIdDoesNotExist() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        boolean deleted = categoriaDao.delete(this.idIncorrecto);
        assertFalse(deleted);
    }

    @Test
    @Order(5)
    @Override
    public void shouldFindIfIdExists() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        Categoria categoria = categoriaDao.search(this.testId);
        assertNotNull(categoria);
    }

    @Test
    @Order(6)
    @Override
    public void shouldNotFindIfIdDoesNotExist() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        Categoria categoria = categoriaDao.search(this.idIncorrecto);
        assertNull(categoria);
    }

    @Test
    @Order(7)
    @Override
    public void shouldList() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        List<Categoria> categorias = categoriaDao.getAll();
        assertNotNull(categorias);
        assertFalse(categorias.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void shouldDeleteIfIdExists() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        boolean deleted = categoriaDao.delete(this.testId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    public void shouldGetCategoriaSons() {
        ICategoriaDAO categoriaDao = new CategoriaDAOImpl();
        // Note: This test assumes a parent category exists in the database
        int parentId = 1; // Replace with a valid parent category ID from your DB
        List<Categoria> sons = categoriaDao.getCategoriaSons(parentId);
        assertNotNull(sons);
    }
    
}
package com.compurangers.platform.service.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.catalog.ICategoriaDAO;
import java.util.List;

public class CategoriaService {
    private final ICategoriaDAO categoriaDAO;

    public CategoriaService(ICategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public int addCategoria(Categoria categoria) {
        return categoriaDAO.add(categoria);
    }

    public boolean updateCategoria(Categoria categoria) {
        return categoriaDAO.update(categoria);
    }

    public boolean deleteCategoria(int id) {
        return categoriaDAO.delete(id);
    }

    public Categoria searchCategoria(int id) {
        return categoriaDAO.search(id);
    }

    public List<Categoria> getAllCategorias() {
        return categoriaDAO.getAll();
    }
}

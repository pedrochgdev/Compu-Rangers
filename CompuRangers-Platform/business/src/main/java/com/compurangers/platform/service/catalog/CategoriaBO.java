package com.compurangers.platform.service.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.catalog.ICategoriaDAO;
import java.util.List;

public class CategoriaBO {
    private final ICategoriaDAO categoriaDAO;

    public CategoriaBO(ICategoriaDAO categoriaDAO) {
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
    
    public Categoria getCategoriaWithParents(int categoriaId) {
        Categoria categoria = categoriaDAO.search(categoriaId);

        if (categoria != null && categoria.getCategoriaPadre() != null) {
            categoria.setCategoriaPadre(getCategoriaWithParents(categoria.getCategoriaPadre().getId()));
        }

        return categoria;
    }

}

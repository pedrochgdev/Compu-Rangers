package com.compurangers.platform.service.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.catalog.ICategoriaDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Categoria> todas = categoriaDAO.getAll(); // este trae todas mapeadas con padre
        Map<Integer, Categoria> mapa = new HashMap<>();

        // Mapear por ID
        for (Categoria cat : todas) {
            mapa.put(cat.getId(), cat);
        }

        // Asignar subcategorías
        for (Categoria cat : todas) {
            Categoria padre = cat.getCategoriaPadre();
            if (padre != null && mapa.containsKey(padre.getId())) {
                mapa.get(padre.getId()).getSubcategorias().add(cat);
            }
        }

        // Devolver solo las raíces
        return todas.stream()
            .filter(c -> c.getCategoriaPadre() == null)
            .collect(Collectors.toList());
    }
    
    public Categoria getCategoriaWithParents(int categoriaId) {
        Categoria categoria = categoriaDAO.search(categoriaId);

        if (categoria != null && categoria.getCategoriaPadre() != null) {
            categoria.setCategoriaPadre(getCategoriaWithParents(categoria.getCategoriaPadre().getId()));
        }

        return categoria;
    }

}

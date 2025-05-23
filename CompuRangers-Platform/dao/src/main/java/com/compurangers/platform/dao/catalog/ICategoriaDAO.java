package com.compurangers.platform.dao.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.ICrud;
import java.util.List;

public interface ICategoriaDAO extends ICrud<Categoria> {
    List<Categoria> getCategoriaSons(int categoriaId);
}

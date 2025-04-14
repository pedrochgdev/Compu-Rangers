package com.compurangers.platform.dao;

import com.compurangers.platform.core.domain.catalog.Categoria;
import java.util.List;

public interface ICategoriaDAO {
    int addCategory(Categoria category);
    int updateCategory(Categoria category);
    int deleteCategory(int id);
    List<Categoria> getAllCategory();
}

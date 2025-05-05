package com.compurangers.platform.dao;

import java.util.List;

public interface IListDetalle<T> extends ICrud<T>{
    List<T> getAllByForeignKey(int foreignKey);
}

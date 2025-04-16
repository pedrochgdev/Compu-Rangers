package com.compurangers.platform.dao;

import java.util.List;

public interface ICrud<T> {
    int add(T modelo);
    boolean update(T modelo);
    boolean delete(int id);
    T search(int id);
    List<T> getAll();
}

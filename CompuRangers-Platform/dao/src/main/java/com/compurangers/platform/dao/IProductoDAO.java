package com.compurangers.platform.dao;

import com.compurangers.platform.core.domain.Product;
import java.util.List;

public interface IProductDAO {
    List<Product> getAllProducts();
}
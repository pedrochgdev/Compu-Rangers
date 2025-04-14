package com.compurangers.platform.dao.mysql;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.compurangers.platform.dao.IProductoDAO;

public class ProductoDAOImpl implements IProductoDAO {
    
    @Override
    public int addProduct(Producto product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int updateProduct(Producto product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteProduct(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<Producto> getAllProducts() {
        List<Producto> products = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            while (rs.next()) {
                Producto product = new Producto();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
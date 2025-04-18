package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.compurangers.platform.dao.catalog.IProductoDAO;

public class ProductoDAOImpl implements IProductoDAO {

    @Override
    public int add(Producto modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Producto modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto search(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> products = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            while (rs.next()) {
                Producto product = new Producto();
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo listar las areas.");
        }
        return products;
    }

}
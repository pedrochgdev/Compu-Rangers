package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.dao.inventory.IInventarioDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAOImpl extends BaseDAOImpl<Inventario> implements IInventarioDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Inventario modelo) throws SQLException {
        String sql = "{call add_inventario(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(2, modelo.getCantidadDisponible());
        cs.setInt(3, modelo.getLoteId());
        cs.setInt(4, modelo.getProducto().getId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Inventario modelo) throws SQLException {
        String sql = "{call update_inventario(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setInt(2, modelo.getCantidadDisponible());
        cs.setInt(3, modelo.getLoteId());
        cs.setInt(4, modelo.getProducto().getId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_inventario(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_inventario(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }
    
    protected CallableStatement getInvDisponibleCommand(Connection conn, int id) throws SQLException {
        String sql = "{call obtener_inventario_disponible(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }
    
    protected CallableStatement getInvReponerCommand(Connection conn, int id) throws SQLException {
        String sql = "{call obtener_inventario_reabastecer(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_inventario()}");
    }

    @Override
    protected Inventario mapModel(ResultSet rs) throws SQLException {
        Inventario inv = new Inventario();
        inv.setId(rs.getInt("id"));
        inv.setCantidadDisponible(rs.getInt("cantidad_disponible"));
        inv.setLoteId(rs.getInt("lote_id"));
        inv.setProducto(new ProductoDAOImpl().search(rs.getInt("producto_id")));
        return inv;
    }

    @Override
    public List<Inventario> getInvDisponible(int id) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.getInvDisponibleCommand(conn, id);
        ) {
            ResultSet rs = cmd.executeQuery();
            
            List<Inventario> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapModel(rs));
            }
            
            return modelos;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
    
    @Override
    public List<Inventario> getInvReponer(int id) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.getInvReponerCommand(conn, id);
        ) {
            ResultSet rs = cmd.executeQuery();
            
            List<Inventario> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapModel(rs));
            }
            
            return modelos;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
}

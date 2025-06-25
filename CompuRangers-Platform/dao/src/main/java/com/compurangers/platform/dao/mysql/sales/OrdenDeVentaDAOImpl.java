package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.sales.IOrdenDeVentaDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class OrdenDeVentaDAOImpl extends BaseDetalleDAOImpl<OrdenDeVenta> implements IOrdenDeVentaDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, OrdenDeVenta modelo) throws SQLException {
        String sql = "{call add_orden_de_venta(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, modelo.getEstado());
        cs.setDate(3, new Date(modelo.getFecha().getTime()));
        cs.setDouble(4, modelo.getTotal());
        cs.setInt(5, modelo.getClienteId());
        cs.setString(6, modelo.getDireccion());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, OrdenDeVenta modelo) throws SQLException {
        String sql = "{call update_orden_de_venta(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getEstado());
        cs.setDate(3, new Date(modelo.getFecha().getTime()));
        cs.setDouble(4, modelo.getTotal());
        cs.setInt(5, modelo.getClienteId());
        cs.setString(6, modelo.getDireccion());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_orden_de_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_orden_de_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_orden_de_venta()}");
    }

    @Override
    protected OrdenDeVenta mapModel(ResultSet rs) throws SQLException {
        OrdenDeVenta ov = new OrdenDeVenta();
        ov.setId(rs.getInt("id"));
        ov.setTotal(rs.getDouble("total"));
        ov.setClienteId(rs.getInt("cliente_usuario_id"));
        ov.setFecha(rs.getDate("fecha"));
        ov.setEstado(rs.getString("estado"));
        ov.setDireccion(rs.getString("direccion"));
        ov.setDetalles(new DetalleVentaDAOImpl().getAllByForeignKey(ov.getId()));
        return ov;
    }
    
    @Override
    public double getTotalHistorico() {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement stmt = conn.prepareCall("{ CALL GET_TOTAL_HISTORICO(?) }");
        ) {
            stmt.registerOutParameter(1, Types.DOUBLE);
            stmt.execute();
            return stmt.getDouble(1);
        } catch (SQLException e) {
            System.err.println("Error SQL en getTotalHistorico: " + e.getMessage());
            throw new RuntimeException("No se pudo obtener el total histórico.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener el total histórico.", e);
        }
    }
    
    @Override
    public int getPedidosHoy() {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement stmt = conn.prepareCall("{ CALL GET_PEDIDOS_HOY(?) }");
        ) {
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.execute();
            return stmt.getInt(1);
        } catch (SQLException e) {
            System.err.println("Error SQL en getPedidosHoy: " + e.getMessage());
            throw new RuntimeException("No se pudo obtener la cantidad de pedidos de hoy.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener la cantidad de pedidos de hoy.", e);
        }
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_ordenes_por_usuario(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
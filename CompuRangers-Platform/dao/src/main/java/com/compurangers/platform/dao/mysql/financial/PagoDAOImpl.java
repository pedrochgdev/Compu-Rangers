package com.compurangers.platform.dao.mysql.financial;

import com.compurangers.platform.core.domain.financial.Pago;
import com.compurangers.platform.dao.financial.IPagoDAO;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PagoDAOImpl extends BaseDetalleDAOImpl<Pago> implements IPagoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Pago modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL add_pago(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        cs.registerOutParameter(1, Types.INTEGER); // OUT generated_id
        cs.setDouble(2, modelo.getMonto());
        cs.setDate(3, new java.sql.Date(modelo.getFechaPago().getTime()));
        cs.setString(4, modelo.getEstado());
        cs.setString(5, modelo.getReferencia());
        
        if (modelo.getDocumentoDeVentasNumero() != 0) {
            cs.setInt(6, modelo.getDocumentoDeVentasNumero());
        } else {
            cs.setNull(6, Types.INTEGER);
        }

        if (modelo.getDocumentoDeComprasNumero() != 0) {
            cs.setInt(7, modelo.getDocumentoDeComprasNumero());
        } else {
            cs.setNull(7, Types.INTEGER);
        }


        cs.setInt(8, modelo.getMetodoDePagoId());
        cs.setInt(9, modelo.getMonedaPeriodoId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Pago modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL update_pago(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        cs.setInt(1, modelo.getId());
        cs.setDouble(2, modelo.getMonto());
        cs.setDate(3, new java.sql.Date(modelo.getFechaPago().getTime()));
        cs.setString(4, modelo.getEstado());
        cs.setString(5, modelo.getReferencia());
        if (modelo.getDocumentoDeVentasNumero() != 0) {
            cs.setInt(6, modelo.getDocumentoDeVentasNumero());
        } else {
            cs.setNull(6, Types.INTEGER);
        }

        if (modelo.getDocumentoDeComprasNumero() != 0) {
            cs.setInt(7, modelo.getDocumentoDeComprasNumero());
        } else {
            cs.setNull(7, Types.INTEGER);
        }
        cs.setInt(8, modelo.getMetodoDePagoId());
        cs.setInt(9, modelo.getMonedaPeriodoId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL delete_pago(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL search_pago(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL get_all_pago()}");
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Este método debe ser implementado por una subclase específica.");
    }

    @Override
    protected Pago mapModel(ResultSet rs) throws SQLException {
        Pago pago = new Pago();
        pago.setId(rs.getInt("id"));
        pago.setMonto(rs.getDouble("monto"));
        pago.setFechaPago(rs.getDate("fecha_pago"));
        pago.setEstado(rs.getString("estado"));
        pago.setReferencia(rs.getString("referencia"));

        pago.setDocumentoDeVentasNumero(rs.getInt("documento_de_ventas_numero"));
        pago.setDocumentoDeComprasNumero(rs.getInt("documento_de_compras_numero"));
        pago.setMetodoDePagoId(rs.getInt("metodo_de_pago_id"));
        pago.setMonedaPeriodoId(rs.getInt("moneda_periodo_id"));

        return pago;
    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

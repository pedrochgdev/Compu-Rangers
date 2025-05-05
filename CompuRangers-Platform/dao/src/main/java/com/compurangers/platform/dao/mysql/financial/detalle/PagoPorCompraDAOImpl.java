package com.compurangers.platform.dao.mysql.financial.detalle;

import com.compurangers.platform.dao.mysql.financial.PagoDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PagoPorCompraDAOImpl extends PagoDAOImpl {
    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL get_pago_by_documento_compra(?)}");
        cs.setInt(1, foreignKey);
        return cs;
    }
}
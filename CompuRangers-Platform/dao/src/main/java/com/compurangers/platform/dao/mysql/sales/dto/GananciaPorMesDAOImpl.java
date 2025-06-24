package com.compurangers.platform.dao.mysql.sales.dto;

import com.compurangers.platform.core.domain.sales.dto.GananciasPorMesDTO;
import com.compurangers.platform.dao.sales.dto.IGananciaPorMesDAO;
import com.compurangers.platform.util.DatabaseUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GananciaPorMesDAOImpl implements IGananciaPorMesDAO {

    @Override
    public List<GananciasPorMesDTO> getGananciasMensuales() {
        List<GananciasPorMesDTO> lista = new ArrayList<>();

        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement stmt = conn.prepareCall("{CALL get_ganancias_mensuales()}")
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date mesStr = rs.getDate("mes");
                BigDecimal ganancia = rs.getBigDecimal("ganancia");

                lista.add(new GananciasPorMesDTO(mesStr, ganancia));
            }

            return lista;

        } catch (SQLException e) {
            System.err.println("Error SQL en getGananciasMensuales: " + e.getMessage());
            throw new RuntimeException("No se pudo obtener las ganancias mensuales.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado en getGananciasMensuales: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener las ganancias mensuales.", e);
        }
    }
}

package com.compurangers.platform.dao.mysql.sales.dto;

import com.compurangers.platform.core.domain.sales.dto.PedidoPorDiaDTO;
import com.compurangers.platform.dao.sales.dto.IPedidoPorDiaDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoPorDiaDAOImpl implements IPedidoPorDiaDAO{

    @Override
    public List<PedidoPorDiaDTO> getPedidosSemanal() {
        
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement stmt = conn.prepareCall("{CALL GET_PEDIDOS_SEMANA()}");
        ) {
            List<PedidoPorDiaDTO> lista = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date dia = rs.getDate("dia");
                int cantidad = rs.getInt("cantidad");
                lista.add(new PedidoPorDiaDTO(dia, cantidad));
            }
            
            return lista;
            
        } catch (SQLException e) {
            System.err.println("Error SQL en getPedidosSemana: " + e.getMessage());
            throw new RuntimeException("No se pudo obtener la cantidad de pedidos de la semana.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener la cantidad de pedidos de la semana.", e);
        }
    }
    
}

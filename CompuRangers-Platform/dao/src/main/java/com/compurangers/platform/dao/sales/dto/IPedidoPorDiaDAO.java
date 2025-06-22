package com.compurangers.platform.dao.sales.dto;

import com.compurangers.platform.core.domain.sales.dto.PedidoPorDiaDTO;
import java.util.List;

public interface IPedidoPorDiaDAO {
    List<PedidoPorDiaDTO> getPedidosSemanal();
}

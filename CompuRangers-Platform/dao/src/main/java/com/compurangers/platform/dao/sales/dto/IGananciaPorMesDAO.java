package com.compurangers.platform.dao.sales.dto;

import com.compurangers.platform.core.domain.sales.dto.GananciasPorMesDTO;
import java.util.List;

public interface IGananciaPorMesDAO {
    List<GananciasPorMesDTO> getGananciasMensuales();
}

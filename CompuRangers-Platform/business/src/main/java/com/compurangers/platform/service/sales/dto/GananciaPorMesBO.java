package com.compurangers.platform.service.sales.dto;

import com.compurangers.platform.core.domain.sales.dto.GananciasPorMesDTO;
import com.compurangers.platform.dao.sales.dto.IGananciaPorMesDAO;
import java.util.List;

public class GananciaPorMesBO {
    private final IGananciaPorMesDAO gan;
    
    public GananciaPorMesBO(IGananciaPorMesDAO gan){
        this.gan = gan;
    }
    
    public List<GananciasPorMesDTO> getGananciasMensuales(){
        return gan.getGananciasMensuales();
    }
}

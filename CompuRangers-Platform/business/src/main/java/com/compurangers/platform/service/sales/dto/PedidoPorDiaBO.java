package com.compurangers.platform.service.sales.dto;

import com.compurangers.platform.core.domain.sales.dto.PedidoPorDiaDTO;
import com.compurangers.platform.dao.sales.dto.IPedidoPorDiaDAO;
import java.util.List;

public class PedidoPorDiaBO {
    
    private final IPedidoPorDiaDAO ped;
    
    public PedidoPorDiaBO(IPedidoPorDiaDAO ped){
        this.ped = ped;
    }
    
    public List<PedidoPorDiaDTO> getPedidosSemana(){
        return ped.getPedidosSemanal();
    }
    
}

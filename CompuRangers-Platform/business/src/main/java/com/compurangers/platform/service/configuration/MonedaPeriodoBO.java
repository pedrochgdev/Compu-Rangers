package com.compurangers.platform.service.configuration;

import com.compurangers.platform.core.domain.configuration.MonedaPeriodo;
import com.compurangers.platform.dao.configuration.IMonedaPeriodoDAO;

public class MonedaPeriodoBO {
    
    private final IMonedaPeriodoDAO mpb;
    
    public MonedaPeriodoBO(IMonedaPeriodoDAO mpb){
        this.mpb = mpb;
    }
    
    public MonedaPeriodo searchWithType(int id, String tipo){
        return mpb.searchWithType(id, tipo);
    }
    
}

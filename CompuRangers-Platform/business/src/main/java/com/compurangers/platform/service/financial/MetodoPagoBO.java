package com.compurangers.platform.service.financial;

import com.compurangers.platform.dao.financial.IMetodoPagoDAO;
import com.compurangers.platform.core.domain.financial.MetodoPago;

public class MetodoPagoBO {
    
    private final IMetodoPagoDAO mpdao;
    
    public MetodoPagoBO(IMetodoPagoDAO mpdao){
        this.mpdao = mpdao;
    }
    
    public MetodoPago searchByName(String name){
        return mpdao.searchByName(name);
    }
    
}

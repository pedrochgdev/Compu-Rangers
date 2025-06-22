package com.compurangers.platform.service.financial;

import com.compurangers.platform.dao.financial.IPagoDAO;
import com.compurangers.platform.core.domain.financial.Pago;

public class PagoBO {
    private final IPagoDAO pdao;
    
    public PagoBO(IPagoDAO pdao){
        this.pdao = pdao;
    }
    
    public int addPago(Pago pago){
        return pdao.add(pago);
    }
}

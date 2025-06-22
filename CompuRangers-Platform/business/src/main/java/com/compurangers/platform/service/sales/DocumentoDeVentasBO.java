package com.compurangers.platform.service.sales;

import com.compurangers.platform.dao.sales.IDocumentoDeVentasDAO;
import com.compurangers.platform.core.domain.sales.DocumentoDeVentas;

public class DocumentoDeVentasBO {
    
    private final IDocumentoDeVentasDAO dvdao;
    
    public DocumentoDeVentasBO(IDocumentoDeVentasDAO dvdao){
        this.dvdao = dvdao;
    }
    
    public int addDocumentoVenta(DocumentoDeVentas dv){
        return dvdao.add(dv);
    }
    
}

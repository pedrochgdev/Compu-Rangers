/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.compurangers.webservice.domain;

import com.compurangers.platform.dao.mysql.catalog.CategoriaDAOImpl;
import com.compurangers.platform.service.catalog.CategoriaBO;
import com.compurangers.platform.core.domain.catalog.Categoria;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

/**
 *
 * @author Intel
 */
@WebService(serviceName = "CategoriaWS", targetNamespace = "http://services.compurangers.com")
public class CategoriaWS {

    private final CategoriaBO cbo;
    
    public CategoriaWS(){
        this.cbo=new CategoriaBO(new CategoriaDAOImpl());
    }
    
    @WebMethod(operationName = "getAllCategorias")
    public List<Categoria> getAllCategorias() {
        return cbo.getAllCategorias();
    }
}

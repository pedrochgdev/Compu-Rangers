/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.compurangers.webservice.domain;

import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.dao.mysql.catalog.MarcaDAOImpl;
import com.compurangers.platform.service.catalog.MarcaBO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "MarcaWS", targetNamespace = "http://services.compurangers.com")
public class MarcaWS {

     private final MarcaBO marca;
    
    public MarcaWS(){
        this.marca=new MarcaBO(new MarcaDAOImpl());
    }
    @WebMethod(operationName = "getAllMarcas")
    public List<Marca> getMarcas() {
        return marca.getAllMarcas();
    }
}

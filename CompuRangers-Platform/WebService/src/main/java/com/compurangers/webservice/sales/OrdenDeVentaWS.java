/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.compurangers.webservice.sales;

import com.compurangers.platform.dao.mysql.sales.OrdenDeVentaDAOImpl;
import com.compurangers.platform.service.sales.OrdenDeVentaBO;
import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "OrdenDeVentaWS", targetNamespace = "http://services.compurangers.com")
public class OrdenDeVentaWS {

    private final OrdenDeVentaBO ov;
    
    public OrdenDeVentaWS(){
        this.ov=new OrdenDeVentaBO(new OrdenDeVentaDAOImpl());
    }
    
    @WebMethod(operationName = "addOrden")
    public int addOrden(@WebParam(name = "orden") OrdenDeVenta orden) {
        return ov.addOrdenDeVenta(orden);
    }
    
    @WebMethod(operationName = "deleteOrden")
    public boolean deleteOrden(@WebParam(name = "orden") int orderId) {
        return ov.deleteOrdenDeVenta(orderId);
    }
}

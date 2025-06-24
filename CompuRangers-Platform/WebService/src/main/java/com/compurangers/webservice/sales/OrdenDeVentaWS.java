/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.compurangers.webservice.sales;

import com.compurangers.platform.dao.mysql.sales.OrdenDeVentaDAOImpl;
import com.compurangers.platform.service.sales.OrdenDeVentaBO;
import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.core.domain.sales.dto.PedidoPorDiaDTO;
import com.compurangers.platform.dao.mysql.sales.dto.PedidoPorDiaDAOImpl;
import com.compurangers.platform.service.sales.dto.PedidoPorDiaBO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "OrdenDeVentaWS", targetNamespace = "http://services.compurangers.com")
public class OrdenDeVentaWS {

    private final OrdenDeVentaBO ov;
    private final PedidoPorDiaBO ped;
    
    public OrdenDeVentaWS(){
        this.ov=new OrdenDeVentaBO(new OrdenDeVentaDAOImpl());
        this.ped=new PedidoPorDiaBO(new PedidoPorDiaDAOImpl());
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

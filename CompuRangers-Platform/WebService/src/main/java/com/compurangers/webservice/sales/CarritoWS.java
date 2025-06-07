package com.compurangers.webservice.sales;

import com.compurangers.platform.dao.mysql.sales.CarritoDAOImpl;
import com.compurangers.platform.service.sales.CarritoBO;
import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.core.domain.sales.Carrito;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "CarritoWS", targetNamespace = "http://services.compurangers.com")
public class CarritoWS {
    private final CarritoBO cbo;
    
    public CarritoWS(){
        this.cbo=new CarritoBO(new CarritoDAOImpl());
    }
    
    @WebMethod(operationName = "addItem")
    public boolean addItem(@WebParam(name = "itemCarrito") ItemCarrito ic) {
        return cbo.addItem(ic);
    }
    
    @WebMethod(operationName = "getCarritoFromUser")
    public Carrito getCarritoFromUser(@WebParam(name = "userId") int userId) {
        return cbo.getCarritoFromUser(userId);
    }
    
    @WebMethod(operationName = "updateCarrito")
    public boolean updateCarrito(@WebParam(name = "carrito") Carrito carrito) {
        return cbo.updateCarrito(carrito);
    }
}

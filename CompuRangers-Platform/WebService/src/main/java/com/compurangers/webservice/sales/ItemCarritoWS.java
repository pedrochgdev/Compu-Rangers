package com.compurangers.webservice.sales;

import com.compurangers.platform.dao.mysql.sales.ItemCarritoDAOImpl;
import com.compurangers.platform.service.sales.ItemCarritoBO;
import com.compurangers.platform.core.domain.sales.ItemCarrito;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "ItemCarritoWS", targetNamespace = "http://services.compurangers.com")
public class ItemCarritoWS {
    private final ItemCarritoBO icbo;
    
    public ItemCarritoWS(){
        this.icbo = new ItemCarritoBO(new ItemCarritoDAOImpl());
    }

    @WebMethod(operationName = "deleteItem")
    public boolean deleteItem(@WebParam(name = "itemId") int itemId) {
        return icbo.deleteItemCarrito(itemId);
    }
    
    @WebMethod(operationName = "updateItem")
    public boolean updateItem(@WebParam(name = "itemCarrito") ItemCarrito item) {
        return icbo.updateItemCarrito(item);
    }
    
    @WebMethod(operationName = "getAllFromCarrito")
    public List<ItemCarrito> getAllFromCarrito(@WebParam(name = "userId") int userId) {
        return icbo.getAllFromCarrito(userId);
    }
}

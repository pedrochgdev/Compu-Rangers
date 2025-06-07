package com.compurangers.webservice.inventory;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.mysql.inventory.InventarioDAOImpl;
import com.compurangers.platform.service.inventory.InventarioBO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebService(serviceName = "InventoryWS", targetNamespace = "http://services.compurangers.com")
public class InventoryWS {
    private final InventarioBO ibo;
    
    public InventoryWS(){
        this.ibo=new InventarioBO(new InventarioDAOImpl());
    }
    @WebMethod(operationName = "getCatalog")
    public List<Producto> getCatalog() {
        Map<Producto, Integer> catalog = ibo.getCatalog();
        List<Producto> productos = new ArrayList<>();

        for (Map.Entry<Producto, Integer> entry : catalog.entrySet()) {
            Producto p = entry.getKey();
            productos.add(p);
        }

        return productos;
    }
}

package com.compurangers.webservice.domain;

import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.service.catalog.ProductoBO;
import com.compurangers.platform.core.domain.catalog.Producto;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "ProductoWS", targetNamespace = "http://services.compurangers.com")
public class ProductoWS {

    private final ProductoBO prod;
    
    public ProductoWS(){
        this.prod=new ProductoBO(new ProductoDAOImpl());
    }
    
    @WebMethod(operationName = "getRanking")
    public List<Producto> getRanking() {
        return prod.getRanking();
    }
    @WebMethod(operationName = "getProductosFiltrados")
    public List<Producto> getSearchAvanzadoProductos(
        @WebParam(name = "nombre") String nombre,
        @WebParam(name = "categoryId") int categoryId,
        @WebParam(name = "marcaId") int marcaId
    ) {
        return prod.getSearchAvanzadoProductos(nombre, categoryId, marcaId);
    }

}

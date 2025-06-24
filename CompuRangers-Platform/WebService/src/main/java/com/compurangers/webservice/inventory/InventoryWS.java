package com.compurangers.webservice.inventory;

import com.compurangers.platform.core.domain.catalog.dto.ProductoDTO;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.mysql.inventory.InventarioDAOImpl;
import com.compurangers.platform.service.inventory.InventarioBO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
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
    public List<ProductoDTO> getCatalog() {
        Map<Producto, Integer> catalog = ibo.getCatalog();
        List<ProductoDTO> productosDTO = new ArrayList<>();

        for (Map.Entry<Producto, Integer> entry : catalog.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();

            ProductoDTO dto = new ProductoDTO(producto, cantidad);
            productosDTO.add(dto);
        }

        return productosDTO;
    }

    
    @WebMethod(operationName = "reservarInventario")
    public void reservarInventario(@WebParam(name = "ordenVenta") OrdenDeVenta ov){
        ibo.reservarInventario(ov);
    }
    
    @WebMethod(operationName = "getCantidadTotalDisponible")
    public int getCantidadTotalDisponible(@WebParam(name = "productoId") int productoId){
        return ibo.getCantidadTotalDisponible(productoId);
    }
    
    @WebMethod(operationName = "getInvDisponible")
    public List<Inventario> getInvDisponible(@WebParam(name = "productoId") int productoId){
        return ibo.getInvDisponible(productoId);
    }
    
}

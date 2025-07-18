package com.compurangers.platform.service.sales;

import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.dao.mysql.sales.ItemCarritoDAOImpl;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import java.util.ArrayList;
import java.util.List;

public class CarritoBO {
    private final ICarritoDAO carritoDAO;

    public CarritoBO(ICarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }

    public int addCarrito(Carrito carrito) {
        return carritoDAO.add(carrito);
    }

    public boolean updateCarrito(Carrito carrito) {
        return carritoDAO.update(carrito);
    }

    public boolean deleteCarrito(int id) {
        return carritoDAO.delete(id);
    }

    public Carrito searchCarrito(int id) {
        return carritoDAO.search(id);
    }

    public List<Carrito> getAllCarrito() {
        return carritoDAO.getAll();
    }
    
    public Carrito getCarritoFromUser(int userId){
        Carrito carrito = carritoDAO.getByForeignKey(userId);

        if (carrito != null) {
            ItemCarritoBO itemCarritoBO = new ItemCarritoBO(new ItemCarritoDAOImpl());
            List<ItemCarrito> items = itemCarritoBO.getAllFromCarrito(carrito.getUsuarioId());
            carrito.setItems(items != null ? items : new ArrayList<>());
        }

        return carrito;
    }

    
    public boolean addItem(ItemCarrito ic){
        ItemCarritoBO itemCarritoBO = new ItemCarritoBO(new ItemCarritoDAOImpl());
        ItemCarrito existente = itemCarritoBO.searchItem(ic.getCarritoId(), ic.getProducto().getId());
        try{
             if(existente != null){
                int nuevaCantidad = existente.getCantidad() + ic.getCantidad();
                double nuevoSubtotal = nuevaCantidad * ic.getProducto().getPrecioVenta();

                existente.setCantidad(nuevaCantidad);
                existente.setSubtotal(nuevoSubtotal);
                
                itemCarritoBO.updateItemCarrito(existente);
             }else{
                itemCarritoBO.addItemCarrito(ic);
             }
             return true;
        }catch(Exception e){
            return false;
        }
    }
    
}

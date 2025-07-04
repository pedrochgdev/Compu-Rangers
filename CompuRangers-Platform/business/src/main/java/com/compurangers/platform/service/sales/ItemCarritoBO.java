package com.compurangers.platform.service.sales;

import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.dao.sales.IItemCarritoDAO;
import java.util.List;

public class ItemCarritoBO {
    private final IItemCarritoDAO itemCarritoDAO;

    public ItemCarritoBO(IItemCarritoDAO itemCarritoDAO) {
        this.itemCarritoDAO = itemCarritoDAO;
    }

    public int addItemCarrito(ItemCarrito itemCarrito) {
        return itemCarritoDAO.add(itemCarrito);
    }

    public boolean updateItemCarrito(ItemCarrito itemCarrito) {
        return itemCarritoDAO.update(itemCarrito);
    }

    public boolean deleteItemCarrito(int id) {
        return itemCarritoDAO.delete(id);
    }

    public ItemCarrito searchItemCarrito(int id) {
        return itemCarritoDAO.search(id);
    }

    public List<ItemCarrito> getAllItemCarrito() {
        return itemCarritoDAO.getAll();
    }
    
    public List<ItemCarrito> getAllFromCarrito(int userId){
        return itemCarritoDAO.getAllByForeignKey(userId);
    }
    
    public ItemCarrito searchItem(int carritoId, int productoId){
        return itemCarritoDAO.searchItem(carritoId, productoId);
    }
    
    public boolean deleteAllByCarritoId(int cid){
        return itemCarritoDAO.deleteAllByCarritoId(cid);
    }
}

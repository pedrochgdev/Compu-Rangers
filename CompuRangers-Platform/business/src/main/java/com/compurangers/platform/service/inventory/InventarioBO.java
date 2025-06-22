package com.compurangers.platform.service.inventory;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.core.domain.sales.DetalleVenta;
import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.inventory.IInventarioDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventarioBO {
    private final IInventarioDAO inventarioDAO;

    public InventarioBO(IInventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    public int addInventario(Inventario inventario) {
        return inventarioDAO.add(inventario);
    }

    public boolean updateInventario(Inventario inventario) {
        return inventarioDAO.update(inventario);
    }

    public boolean deleteInventario(int id) {
        return inventarioDAO.delete(id);
    }

    public Inventario searchInventario(int id) {
        return inventarioDAO.search(id);
    }

    public List<Inventario> getAllInventarios() {
        return inventarioDAO.getAll();
    }
    
    public Map<Producto, Integer> getCatalog(){
        Map<Producto, Integer> totalPorProducto = new HashMap<>();
    
        for (Inventario inv : getAllInventarios()) {
            Producto producto = inv.getProducto();
            int cantidad = inv.getCantidadDisponible();

            totalPorProducto.merge(producto, cantidad, Integer::sum);
        }

        return totalPorProducto;
    }
    
    public List<Inventario> getInvDisponible(int id){
        return inventarioDAO.getInvDisponible(id);
    }
    
    public void reservarInventario(OrdenDeVenta ov){
        for (DetalleVenta detalle : ov.getDetalles()) {
            int cantidadAReducir = detalle.getCantidad();
            int productoId = detalle.getProducto().getId();
            
            List<Inventario> lotes = this.getInvDisponible(productoId); // ordenados por id ascendente

            for (Inventario lote : lotes) {
                if (cantidadAReducir <= 0) break;

                int disponible = lote.getCantidadDisponible();

                if (disponible >= cantidadAReducir) {
                    lote.setCantidadDisponible(disponible - cantidadAReducir);
                    this.updateInventario(lote);
                    cantidadAReducir = 0;
                } else {
                    lote.setCantidadDisponible(0);
                    this.updateInventario(lote);
                    cantidadAReducir -= disponible;
                }
            }

            if (cantidadAReducir > 0) {
                System.err.println("❗ No hay suficiente stock para el producto ID: " + productoId);
                // podrías lanzar una excepción o registrar este error
            }
        }
    }
    
    public List<Inventario> getInventarioPorProductoDesc(int id){
        return inventarioDAO.getInvReponer(id);
    }    
}

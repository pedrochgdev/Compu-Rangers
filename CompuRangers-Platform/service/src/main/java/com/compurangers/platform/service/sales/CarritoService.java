package com.compurangers.platform.service.sales;

import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import java.util.List;

public class CarritoService {
    private final ICarritoDAO carritoDAO;

    public CarritoService(ICarritoDAO carritoDAO) {
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
}

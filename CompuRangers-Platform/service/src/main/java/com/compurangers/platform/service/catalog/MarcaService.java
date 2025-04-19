package com.compurangers.platform.service.catalog;

import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.dao.catalog.IMarcaDAO;
import java.util.List;

public class MarcaService {
    private final IMarcaDAO marcaDAO;

    public MarcaService(IMarcaDAO marcaDAO) {
        this.marcaDAO = marcaDAO;
    }

    public int addMarca(Marca marca) {
        return marcaDAO.add(marca);
    }

    public boolean updateMarca(Marca marca) {
        return marcaDAO.update(marca);
    }

    public boolean deleteMarca(int id) {
        return marcaDAO.delete(id);
    }

    public Marca searchMarca(int id) {
        return marcaDAO.search(id);
    }

    public List<Marca> getAllMarcas() {
        return marcaDAO.getAll();
    }
}

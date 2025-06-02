package com.compurangers.platform.service.inventory;

import com.compurangers.platform.core.domain.inventory.Lote;
import com.compurangers.platform.dao.inventory.ILoteDAO;
import java.util.List;

public class LoteBO {
    private final ILoteDAO loteDAO;

    public LoteBO(ILoteDAO loteDAO) {
        this.loteDAO = loteDAO;
    }

    public int addLote(Lote lote) {
        return loteDAO.add(lote);
    }

    public boolean updateLote(Lote lote) {
        return loteDAO.update(lote);
    }

    public boolean deleteLote(int id) {
        return loteDAO.delete(id);
    }

    public Lote searchLote(int id) {
        return loteDAO.search(id);
    }

    public List<Lote> getAllLotes() {
        return loteDAO.getAll();
    }
}

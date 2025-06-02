package com.compurangers.platform.service.inventory;

import com.compurangers.platform.core.domain.inventory.DetalleLote;
import com.compurangers.platform.dao.inventory.IDetalleLoteDAO;
import java.util.List;

public class DetalleLoteBO {
    private final IDetalleLoteDAO detalleLoteDAO;

    public DetalleLoteBO(IDetalleLoteDAO detalleLoteDAO) {
        this.detalleLoteDAO = detalleLoteDAO;
    }

    public int addDetalleLote(DetalleLote detalleLote) {
        return detalleLoteDAO.add(detalleLote);
    }

    public boolean updateDetalleLote(DetalleLote detalleLote) {
        return detalleLoteDAO.update(detalleLote);
    }

    public boolean deleteDetalleLote(int id) {
        return detalleLoteDAO.delete(id);
    }

    public DetalleLote searchDetalleLote(int id) {
        return detalleLoteDAO.search(id);
    }

    public List<DetalleLote> getAllDetalleLotes() {
        return detalleLoteDAO.getAll();
    }
}

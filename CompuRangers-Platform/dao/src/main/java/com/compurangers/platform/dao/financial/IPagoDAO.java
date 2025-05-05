package com.compurangers.platform.dao.financial;

import com.compurangers.platform.core.domain.financial.Pago;
import com.compurangers.platform.dao.ICrud;
import java.util.List;

public interface IPagoDAO extends ICrud<Pago> {
    List<Pago> getAllByForeignKey(int foreignKey);
}

package com.compurangers.platform.dao.configuration;

import com.compurangers.platform.core.domain.configuration.MonedaPeriodo;
import com.compurangers.platform.dao.ICrud;

public interface IMonedaPeriodoDAO extends ICrud<MonedaPeriodo>{
    MonedaPeriodo searchWithType(int id, String tipo);
}

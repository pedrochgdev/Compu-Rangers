package com.compurangers.platform.service.user;

import com.compurangers.platform.dao.user.ILogDAO;
import com.compurangers.platform.core.domain.user.Log;
import java.util.Date;

public class LogBO {
    private final ILogDAO logBo;
    
    public LogBO(ILogDAO log){
        this.logBo = log;
    } 
    
    public int addLog(Log log){
        log.setFecha(new Date());
        return logBo.add(log);
    }
}

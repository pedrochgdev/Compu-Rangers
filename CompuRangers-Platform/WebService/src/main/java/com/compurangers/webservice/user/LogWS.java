package com.compurangers.webservice.user;

import com.compurangers.platform.dao.mysql.user.LogDAOImpl;
import com.compurangers.platform.service.user.LogBO;
import com.compurangers.platform.core.domain.user.Log;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "LogWS")
public class LogWS {
    
    private final LogBO logbo;
    
    public LogWS(){
        this.logbo= new LogBO(new LogDAOImpl());
    }
    
    @WebMethod(operationName = "addLog")
    public int addLog(@WebParam(name = "log") Log log) {
        return logbo.addLog(log);
    }
}

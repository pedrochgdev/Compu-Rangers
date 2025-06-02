package com.compurangers.webservice.user.auth;

import com.compurangers.platform.dao.mysql.user.UsuarioDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.service.user.UsuarioBO;
import com.compurangers.platform.service.user.auth.AuthService;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "AuthWS", targetNamespace = "http://services.compurangers.com")
public class AuthWS {
    private final AuthService authS; 
    
    public AuthWS() {
        this.authS = new AuthService(new UsuarioBO(new UsuarioDAOImpl(), new TokenRecuperacionBO(new TokenRecuperacionDAOImpl())));
    }
    
    @WebMethod(operationName = "login")
    public int login(@WebParam(name = "user") String user, @WebParam(name = "password") String password) {
        return authS.login(user, password);
    }
}

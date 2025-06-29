package com.compurangers.webservice.user.auth;

import com.compurangers.platform.dao.mysql.user.UsuarioDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.service.user.UsuarioBO;
import com.compurangers.platform.service.user.auth.AuthService;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.core.domain.user.auth.TokenRecuperacion;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "TokenRecuperacionWS", targetNamespace = "http://services.compurangers.com")
public class TokenRecuperacionWS {
    
    private final TokenRecuperacionBO tokenBO; 
    
    public TokenRecuperacionWS() {
        this.tokenBO = new TokenRecuperacionBO(new TokenRecuperacionDAOImpl());
    }
    
    @WebMethod(operationName = "searchToken")
    public TokenRecuperacion searchToken(@WebParam(name = "token") String token) {
        return tokenBO.searchToken(token);
    }
    
    @WebMethod(operationName = "markTokenAsUsed")
    public void markTokenAsUsed(@WebParam(name = "id") int id) {
        tokenBO.markTokenAsUsed(id);
    }
}

package com.compurangers.webservice.user;

import com.compurangers.platform.dao.mysql.user.UsuarioDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.service.user.UsuarioBO;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "UsuarioWS", targetNamespace = "http://services.compurangers.com")
public class UsuarioWS {
    private final UsuarioBO user; 
    
    public UsuarioWS() {
        this.user = new UsuarioBO(new UsuarioDAOImpl(), new TokenRecuperacionBO(new TokenRecuperacionDAOImpl()));
    }
    @WebMethod(operationName = "updateUsuario")
    public boolean updateUsuario(@WebParam(name = "usuario") Usuario usuario) {
        return user.updateUsuario(usuario);
    }
    
    @WebMethod(operationName = "changePassword")
    public boolean changePassword(@WebParam(name = "usuarioId") int usuario, @WebParam(name = "password") String password, @WebParam(name = "tokenId") int tokenId) {
        return user.changePassword(usuario, password, tokenId);
    }
    
    @WebMethod(operationName = "forgotPassword")
    public boolean forgotPassword(@WebParam(name = "correo") String correo) {
        return user.forgotPassword(correo);
    }
    
    @WebMethod(operationName = "deleteUsuario")
    public boolean deleteUsuario(@WebParam(name = "usuarioId") int usuario) {
        return user.deleteUsuario(usuario);
    }
    
    @WebMethod(operationName = "searchUsuario")
    public Usuario searchUsuario(@WebParam(name = "usuarioId") int usuario) {
        return user.searchUsuario(usuario);
    }
    
    @WebMethod(operationName = "getRole")
    public boolean getRole(@WebParam(name = "usuarioId") int usuario) {
        return user.searchUsuario(usuario).isAdmin();
    }
    
}

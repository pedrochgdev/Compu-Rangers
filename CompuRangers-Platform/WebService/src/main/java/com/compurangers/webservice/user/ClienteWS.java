package com.compurangers.webservice.user;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.mysql.sales.CarritoDAOImpl;
import com.compurangers.platform.dao.mysql.user.ClienteDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.service.payment.PaymentService;
import com.compurangers.platform.service.user.ClienteBO;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.mysql.user.UsuarioDAOImpl;
import com.compurangers.platform.service.user.UsuarioBO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "ClienteWS", targetNamespace = "http://services.compurangers.com")
public class ClienteWS {
    private final ClienteBO user; 
    private final UsuarioBO user_bo; 
    
    public ClienteWS() {
        this.user = new ClienteBO(new ClienteDAOImpl(), new TokenRecuperacionBO(new TokenRecuperacionDAOImpl()), new CarritoDAOImpl(), new PaymentService());
        this.user_bo = new UsuarioBO(new UsuarioDAOImpl(), new TokenRecuperacionBO(new TokenRecuperacionDAOImpl()));
    }
    
    @WebMethod(operationName = "addCliente")
    public int addCliente(@WebParam(name = "cliente") Cliente cliente) {
        return user.addCliente(cliente);
    }
    
    @WebMethod(operationName = "searchCliente")
    public Cliente searchCliente(@WebParam(name = "id") int id) {
        return user.searchCliente(id);
    }
    
    @WebMethod(operationName = "payment")
    public String payment(@WebParam(name = "ordenVenta") OrdenDeVenta ov) {
        return user.payment(ov);
    }
    
    @WebMethod(operationName = "updateCliente")
    public boolean updateCliente(@WebParam(name = "cliente") Cliente cliente) {
        if(!user_bo.updateUsuario((Usuario)cliente)){
            return false;
        }
        return user.updateCliente(cliente);
    }
    
    
    @WebMethod(operationName = "getClientesNuevos")
    public int getClientesNuevos() {
        return user.getClientesNuevos();
    }
}

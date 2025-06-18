package com.compurangers.webservice.user;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.mysql.sales.CarritoDAOImpl;
import com.compurangers.platform.dao.mysql.user.ClienteDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.service.payment.PaymentService;
import com.compurangers.platform.service.user.ClienteBO;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.core.domain.user.Cliente;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "ClienteWS", targetNamespace = "http://services.compurangers.com")
public class ClienteWS {
    private final ClienteBO user; 
    
    public ClienteWS() {
        this.user = new ClienteBO(new ClienteDAOImpl(), new TokenRecuperacionBO(new TokenRecuperacionDAOImpl()), new CarritoDAOImpl(), new PaymentService());
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
}

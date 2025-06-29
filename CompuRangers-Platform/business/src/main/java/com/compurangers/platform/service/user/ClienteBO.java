package com.compurangers.platform.service.user;

import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.mysql.sales.DetalleVentaDAOImpl;
import com.compurangers.platform.dao.mysql.sales.OrdenDeVentaDAOImpl;
import com.compurangers.platform.dao.mysql.user.ClienteDAOImpl;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import com.compurangers.platform.dao.user.IClienteDAO;
import com.compurangers.platform.service.payment.PaymentService;
import com.compurangers.platform.service.sales.DetalleVentaBO;
import com.compurangers.platform.service.sales.OrdenDeVentaBO;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;

public class ClienteBO extends UsuarioBO<IClienteDAO> {
    private final ICarritoDAO carritoDAO;
    private final PaymentService paymentService;

    public ClienteBO(IClienteDAO usuarioDAO, TokenRecuperacionBO tokenService, ICarritoDAO carritoDAO, PaymentService paymentService) {
        super(usuarioDAO, tokenService);
        this.carritoDAO = carritoDAO;
        this.paymentService = paymentService;
    }
    
    public int addCliente(Cliente usuario) {
        usuario.setContrasena(PasswordUtils.hashPassword(usuario.getContrasena()));
        usuario.setDireccionPreferida(usuario.getDireccion());
        int userId = usuarioDAO.add(usuario);
        System.out.println("Nuevo usuario agregado con ID: " + userId);
        if (userId > 0) {
            Carrito carrito = new Carrito();
            carrito.setUsuarioId(userId);
            carrito.setTotal(0.0);
            carritoDAO.add(carrito);
        }
        return userId;
    }
    public boolean updateCliente(Cliente usuario){
        return usuarioDAO.update(usuario);
    }
    public Cliente searchCliente(int id){
        IClienteDAO ic = new ClienteDAOImpl();
        return (Cliente)ic.search(id);
    }
    
    private void crearDetalle(OrdenDeVenta ov) {
        DetalleVentaBO detalleVentaBO = new DetalleVentaBO(new DetalleVentaDAOImpl());

        for (var detalle : ov.getDetalles()) {
            detalleVentaBO.addDetalleVenta(detalle);
        }
    }

    
    public String payment(OrdenDeVenta ov) {
        Usuario user =  usuarioDAO.search(ov.getClienteId());
        String email = user.getCorreoElectronico();
        String amount = String.valueOf((int) (ov.getTotal() * 100));
        String currency = "604"; //soles
        String mode = "TEST";
        String language = "es";
        String orderId = "order-" + ov.getId();
        
        String urlPago = paymentService.generarPago(email, amount, currency, mode, language, orderId);
        
        if (urlPago != null && urlPago.startsWith("http")) {
            crearDetalle(ov);
            return urlPago;
        } else {
            throw new RuntimeException("No se pudo generar la URL de pago. Total=" + String.valueOf((int) (ov.getTotal() * 100)));
        }
    }
    
    public int getClientesNuevos(){
        IClienteDAO ic = new ClienteDAOImpl();
        return ic.getClientesNuevos();
    }
}
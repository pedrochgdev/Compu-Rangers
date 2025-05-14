package com.compurangers.platform.service.user;

import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import com.compurangers.platform.dao.user.IClienteDAO;
import com.compurangers.platform.service.payment.PaymentService;
import com.compurangers.platform.service.user.auth.TokenRecuperacionService;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;

public class ClienteService extends UsuarioService<IClienteDAO> {
    private final ICarritoDAO carritoDAO;
    private final PaymentService paymentService;

    public ClienteService(IClienteDAO usuarioDAO, TokenRecuperacionService tokenService, ICarritoDAO carritoDAO, PaymentService paymentService) {
        super(usuarioDAO, tokenService);
        this.carritoDAO = carritoDAO;
        this.paymentService = paymentService;
    }

    public int addUsuario(Cliente usuario) {
        usuario.setContrasena(PasswordUtils.hashPassword(usuario.getContrasena()));
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
    
    public void payment(int usuarioId) {
        Usuario usuario = usuarioDAO.search(usuarioId);
        String email = usuario.getCorreoElectronico(); //"example12@gmail.com";
        String amount = "100";//String.valueOf(carritoDAO.getByForeignKey(usuarioId).getTotal());
        String currency = "604";
        String mode = "TEST";
        String language = "es";
        String orderId = "order-" + usuarioId;

        String urlPago = paymentService.generarPago(email, amount, currency, mode, language, orderId);

        if (urlPago != null && urlPago.startsWith("http")) {
            paymentService.redireccionarAPago(urlPago);
        } else {
            System.out.println("No se pudo generar la URL de pago. Verifique los detalles del pago o intente nuevamente.");
        }
    }
}
package com.compurangers.platform.service.user;

import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import com.compurangers.platform.dao.user.IUsuarioDAO;
import com.compurangers.platform.service.payment.PaymentService;
import com.compurangers.platform.service.user.auth.TokenRecuperacionService;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;
import com.compurangers.platform.service.utils.EmailService;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioService {
    private final IUsuarioDAO usuarioDAO;
    private final TokenRecuperacionService tokenService;
    private final ICarritoDAO carritoDAO;
    private final PaymentService paymentService;

    public UsuarioService(IUsuarioDAO usuarioDAO, TokenRecuperacionService tokenService, ICarritoDAO carritoDAO, PaymentService paymentService) {
        this.usuarioDAO = usuarioDAO;
        this.carritoDAO = carritoDAO;
        this.tokenService = tokenService;
        this.paymentService = paymentService;
    }

    public int addUsuario(Usuario usuario) {
        usuario.setContrasena(PasswordUtils.hashPassword(usuario.getContrasena()));
        int userId = usuarioDAO.add(usuario);
        if (userId > 0) {
            Carrito carrito = new Carrito();
            carrito.setUsuarioId(userId);
            carrito.setTotal(0.0);
            carritoDAO.add(carrito);
        }
        return userId;
    }

    public boolean updateUsuario(Usuario usuario) {
        return usuarioDAO.update(usuario);
    }
    
    public boolean changePassword(int userID, String newPlainPassword) {
        String hashed = PasswordUtils.hashPassword(newPlainPassword);
        return usuarioDAO.updatePassword(userID, hashed);
    }
    
    public boolean forgotPassword(String correo){
        int user = getUserIdByField(correo);
        if(user>0){
            LocalDateTime fechaExpiracion = LocalDateTime.now().plusMinutes(5);
            String token = tokenService.generateToken(user, fechaExpiracion);
            EmailService emailService = new EmailService();
            emailService.sendPasswordRecoveryEmail(correo, token);
            return true;
        }
        return false;
    }
    
    public void payment(int usuarioId) {
        Usuario usuario = usuarioDAO.search(usuarioId);
        String email = usuario.getCorreoElectronico();
        double amount = carritoDAO.getByForeignKey(usuarioId).getTotal();
        String currency = "604";
        String mode = "TEST";
        String language = "es";
        String orderId = "order-" + usuarioId;

        String urlPago = paymentService.generarPago(email, amount, currency, mode, language, orderId);
    
        if (urlPago != null) {
            paymentService.redireccionarAPago(urlPago);
        } else {
            System.out.println("Error al generar el pago.");
        }
    }


    public boolean deleteUsuario(int id) {
        return usuarioDAO.delete(id);
    }

    public Usuario searchUsuario(int id) {
        return usuarioDAO.search(id);
    }

    public List<Usuario> getAllUsuario() {
        return usuarioDAO.getAll();
    }
    
    public int getUserIdByField(String input){
        int userID;
        
        if((userID=usuarioDAO.getUserByField("email", input))>0 || (userID=usuarioDAO.getUserByField("username", input))>0){
            //Encontro el User
            return userID;
        }
        return -1;
    }
    
    public String getPasswordHash(int userID) {
       return usuarioDAO.getPasswordHash(userID);
    }
    
}

package com.compurangers.platform.appprueba;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.mysql.sales.CarritoDAOImpl;
import com.compurangers.platform.dao.mysql.sales.OrdenDeVentaDAOImpl;
import com.compurangers.platform.dao.mysql.user.ClienteDAOImpl;
import com.compurangers.platform.dao.mysql.user.UsuarioDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import com.compurangers.platform.dao.user.IClienteDAO;
import com.compurangers.platform.dao.user.auth.ITokenRecuperacionDAO;
import com.compurangers.platform.service.catalog.ProductoBO;
import com.compurangers.platform.service.payment.PaymentService;
import com.compurangers.platform.service.sales.OrdenDeVentaBO;
import com.compurangers.platform.service.user.ClienteBO;
import com.compurangers.platform.service.user.UsuarioBO;
import com.compurangers.platform.service.user.auth.AuthService;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;
import com.compurangers.platform.service.utils.EmailService;
import java.util.Date;
import java.util.List;

public class AppPrueba {
    public static void main(String[] args) {
                  
         // Instancias necesarias para el servicio
        IClienteDAO usuarioDAO = new ClienteDAOImpl(); // Tu implementación real
        ICarritoDAO carritoDAO = new CarritoDAOImpl(); // Tu implementación real
        ITokenRecuperacionDAO tokenDAO = new TokenRecuperacionDAOImpl(); // Tu implementación real
        TokenRecuperacionBO tokenService = new TokenRecuperacionBO(tokenDAO);
        PaymentService paymentService = new PaymentService(); // Tu implementación real
        
        OrdenDeVentaBO ob=new OrdenDeVentaBO(new OrdenDeVentaDAOImpl());
        
//        List<OrdenDeVenta> lob=ob.getOrdenesFromUsuario(3);
        
        
        // Instanciar UsuarioService
        ClienteBO usuarioService = new ClienteBO(usuarioDAO, tokenService, carritoDAO, paymentService);
        ProductoBO pbo= new ProductoBO(new ProductoDAOImpl());
        pbo.getSearchAvanzadoProductos("", 0, 0);
//
//        // Crear nuevo usuario
//        Cliente nuevoUsuario = new Cliente();
//        nuevoUsuario.setUsername("johndoe1");
//        nuevoUsuario.setCorreoElectronico("johndoe1@example.com");
//        nuevoUsuario.setContrasena("1234password");
//        nuevoUsuario.setNombreCompleto("John Doe1");
//        nuevoUsuario.setTelefono("940173922");
//        nuevoUsuario.setDireccion("av. amen 124");
//        nuevoUsuario.setDireccionPreferida("av. amen 124");
//
//        // Agregar usuario
//        int userId = usuarioService.addUsuario(nuevoUsuario);
//        System.out.println("Nuevo usuario agregado con ID: " + userId);
//
//        // Cambiar contraseña
//        boolean cambioPassword = usuarioService.changePassword(userId, "newpassword123");
//        System.out.println("¿Se cambió la contraseña? " + cambioPassword);
//
//        // Simular recuperación de contraseña
//        boolean recoveryStatus = usuarioService.forgotPassword("johndoe@example.com");
//        System.out.println("¿Se envió correo de recuperación? " + recoveryStatus);
//
        // Simular pago
//        OrdenDeVentaBO obo = new OrdenDeVentaBO(new OrdenDeVentaDAOImpl());
//        int o = usuarioService.getClientesNuevos();
//        double i = obo.getTotalHistorico();

//        // Actualizar usuario
//        nuevoUsuario.setId(userId); // Asegúrate de asignar el ID para la actualización
//        nuevoUsuario.setNombreCompleto("John Doe Jr.");
//        boolean updateStatus = usuarioService.updateUsuario(nuevoUsuario);
//        System.out.println("¿Se actualizó el usuario? " + updateStatus);
//
//        // Buscar usuario
//        Usuario usuarioBuscado = usuarioService.searchUsuario(userId);
//        if (usuarioBuscado != null) {
//            System.out.println("Usuario encontrado: " + usuarioBuscado.getNombreCompleto());
//        } else {
//            System.out.println("Usuario no encontrado.");
//        }
//
//        // Obtener todos los usuarios
//        List<Usuario> usuarios = usuarioService.getAllUsuario();
//        System.out.println("Todos los usuarios:");
//        for (Usuario usuario : usuarios) {
//            System.out.println("- " + usuario.getUsername() + " (" + usuario.getCorreoElectronico() + ")");
//        }
//
//        // Obtener hash de contraseña
//        String passwordHash = usuarioService.getPasswordHash(userId);
//        System.out.println("Hash de la contraseña: " + passwordHash);
//
//        // Eliminar usuario
//        boolean deleteStatus = usuarioService.deleteUsuario(userId);
//        System.out.println("¿Se eliminó el usuario? " + deleteStatus);

          UsuarioBO user = new UsuarioBO(new UsuarioDAOImpl(), tokenService);
          
          user.forgotPassword("gabrielchg6@gmail.com");

        EmailService emailService = new EmailService();
        String emailDestino = "gabrielchg2004@gmail.com"; // Cambia esto por uno real que controles
        String token = "PRUEBA-TOKEN-12345";

        emailService.sendPasswordRecoveryEmail(emailDestino, token);
        System.out.println("Correo enviado. Revisa tu bandeja.");
    }
}
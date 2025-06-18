package com.compurangers.platform.appprueba;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.mysql.sales.CarritoDAOImpl;
import com.compurangers.platform.dao.mysql.user.ClienteDAOImpl;
import com.compurangers.platform.dao.mysql.user.UsuarioDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import com.compurangers.platform.dao.user.IClienteDAO;
import com.compurangers.platform.dao.user.auth.ITokenRecuperacionDAO;
import com.compurangers.platform.service.payment.PaymentService;
import com.compurangers.platform.service.user.ClienteBO;
import com.compurangers.platform.service.user.UsuarioBO;
import com.compurangers.platform.service.user.auth.AuthService;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;
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

        // Instanciar UsuarioService
        ClienteBO usuarioService = new ClienteBO(usuarioDAO, tokenService, carritoDAO, paymentService);
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
        OrdenDeVenta ov = new OrdenDeVenta();
        
        ov.setClienteId(2);
        ov.setDireccion("456 Client Ave");
        ov.setFecha(new Date());
        ov.setTotal(2799.98);
        ov.setEstado("EN_PROCESO");
        
        String abc = usuarioService.payment(ov);
        
        System.out.println(abc);

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
    }
}
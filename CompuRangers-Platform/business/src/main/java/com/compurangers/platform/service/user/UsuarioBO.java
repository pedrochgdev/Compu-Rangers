package com.compurangers.platform.service.user;

import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.user.IUsuarioDAO;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;
import com.compurangers.platform.service.utils.EmailService;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioBO<T extends IUsuarioDAO> {
    protected final T usuarioDAO;
    private final TokenRecuperacionBO tokenService;

    public UsuarioBO(T usuarioDAO, TokenRecuperacionBO tokenService) {
        this.usuarioDAO = usuarioDAO;
        this.tokenService = tokenService;
    }

    public boolean updateUsuario(Usuario usuario) {
        return usuarioDAO.update(usuario);
    }

    public boolean changePassword(int userID, String newPlainPassword) {
        String hashed = PasswordUtils.hashPassword(newPlainPassword);
        return usuarioDAO.updatePassword(userID, hashed);
    }

    public boolean forgotPassword(String correo) {
        int user = getUserIdByField(correo);
        if (user > 0) {
            LocalDateTime fechaExpiracion = LocalDateTime.now().plusMinutes(5);
            String token = tokenService.generateToken(user, fechaExpiracion);
            EmailService emailService = new EmailService();
            emailService.sendPasswordRecoveryEmail(correo, token);
            return true;
        }
        return false;
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

    public int getUserIdByField(String input) {
        int userID = usuarioDAO.getUserByField("email", input);
        if (userID <= 0) {
            userID = usuarioDAO.getUserByField("username", input);
        }
        return (userID > 0) ? userID : -1;
    }

    public String getPasswordHash(int userID) {
        return usuarioDAO.getPasswordHash(userID);
    }
}
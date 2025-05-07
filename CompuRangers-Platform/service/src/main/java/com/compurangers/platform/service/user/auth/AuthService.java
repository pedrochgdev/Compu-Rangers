package com.compurangers.platform.service.user.auth;

import com.compurangers.platform.service.user.UsuarioService;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;

public class AuthService {

    private final UsuarioService usuarioService;

    public AuthService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    /**
     * Intenta autenticar al usuario por email o username.
     * 
     * @param userField puede ser email o username
     * @param password contraseña en texto plano
     * @return ID del usuario si es exitoso, -1 si falla
     */
    public int login(String userField, String password) {
        int userID = usuarioService.getUserIdByField(userField);
        if (userID > 0 && passwordMatches(userID, password)) {
            return userID;
        }
        return -1;
    }
    
    private boolean passwordMatches(int userID, String inputPassword) {
        String storedHash = usuarioService.getPasswordHash(userID);
        return storedHash != null && PasswordUtils.checkPassword(inputPassword, storedHash);
    }
    
}

package com.compurangers.platform.service.user;

import com.compurangers.platform.dao.user.IAdminDAO;
import com.compurangers.platform.service.user.auth.TokenRecuperacionService;

public class AdminService extends UsuarioService<IAdminDAO> {

    public AdminService(IAdminDAO usuarioDAO, TokenRecuperacionService tokenService) {
        super(usuarioDAO, tokenService);
    }

    // Add admin-specific methods here if needed
}
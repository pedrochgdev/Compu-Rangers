package com.compurangers.platform.service.user;

import com.compurangers.platform.dao.user.IAdminDAO;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;
import com.compurangers.platform.core.domain.user.Admin;
import java.util.Date;

public class AdminBO extends UsuarioBO<IAdminDAO> {

    public AdminBO(IAdminDAO usuarioDAO, TokenRecuperacionBO tokenService) {
        super(usuarioDAO, tokenService);
    }

    public int addAdmin(Admin usuario) {
        usuario.setContrasena(PasswordUtils.hashPassword(usuario.getContrasena()));
        usuario.setFechaIngreso(new Date());
        int userId = usuarioDAO.add(usuario);
        System.out.println("Nuevo usuario agregado con ID: " + userId);
        return userId;
    }
}
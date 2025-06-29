package com.compurangers.platform.service.user;

import com.compurangers.platform.dao.user.IAdminDAO;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import com.compurangers.platform.service.user.auth.utils.PasswordUtils;
import com.compurangers.platform.core.domain.user.Admin;
import com.compurangers.platform.dao.mysql.user.AdminDAOImpl;
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
    
    public double getGananciaMes(){
        IAdminDAO ic = new AdminDAOImpl();
        return ic.getGananciaMes();
    }
    public Admin searchAdmin(int id){
        IAdminDAO ic = new AdminDAOImpl();
        return (Admin)ic.search(id);
    }
    public boolean updateAdmin(Admin usuario){
        return usuarioDAO.update(usuario);
    }
}
package com.compurangers.platform.service.user;

import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Admin;
import com.compurangers.platform.dao.user.IUsuarioDAO;
import java.util.List;

public class UsuarioService {
    private final IUsuarioDAO usuarioDAO;

    public UsuarioService(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public int addUsuario(Usuario usuario) {
        return usuarioDAO.add(usuario); // puede ser Usuario, Cliente o Admin
    }

    public boolean updateUsuario(Usuario usuario) {
        return usuarioDAO.update(usuario);
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

    public List<Cliente> getAllClientes() {
        return usuarioDAO.getAllClientes();
    }

    public List<Admin> getAllAdmins() {
        return usuarioDAO.getAllAdmins();
    }
    
}

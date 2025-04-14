package com.compurangers.platform.dao;

import com.compurangers.platform.core.domain.user.Usuario;
import java.util.List;

public interface IUsuarioDAO {
    int addUser(Usuario user);
    int updateUser(Usuario user);
    int deleteUser(int id);
    List<Usuario> getAllUsers();
}

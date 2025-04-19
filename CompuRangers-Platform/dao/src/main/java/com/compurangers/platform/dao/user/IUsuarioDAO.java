package com.compurangers.platform.dao.user;

import com.compurangers.platform.core.domain.user.Admin;
import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.ICrud;
import java.util.List;

public interface IUsuarioDAO extends ICrud<Usuario>{
    Usuario login(String correo, String contraseña);
    boolean existsByEmail(String correo);
    List<Cliente> getAllClientes();
    List<Admin> getAllAdmins();
}

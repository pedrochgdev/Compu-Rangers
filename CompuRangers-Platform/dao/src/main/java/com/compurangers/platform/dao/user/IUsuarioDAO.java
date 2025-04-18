package com.compurangers.platform.dao.user;

import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.ICrud;

public interface IUsuarioDAO extends ICrud<Usuario>{
    Usuario login(String correo, String contraseña);
    boolean existsByEmail(String correo);
}

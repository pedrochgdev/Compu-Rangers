package com.compurangers.platform.dao.user;

import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.ICrud;

public interface IUsuarioDAO extends ICrud<Usuario>{
    int getUserByField(String field, String value);
    String getPasswordHash(int userId);
}

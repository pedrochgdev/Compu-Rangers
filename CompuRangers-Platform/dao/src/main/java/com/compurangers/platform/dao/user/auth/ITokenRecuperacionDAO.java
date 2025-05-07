package com.compurangers.platform.dao.user.auth;

import com.compurangers.platform.core.domain.user.auth.TokenRecuperacion;
import com.compurangers.platform.dao.ICrud;

public interface ITokenRecuperacionDAO extends ICrud<TokenRecuperacion>{
    TokenRecuperacion searchByToken(String token);
    void markTokenAsUsed(int id);
}

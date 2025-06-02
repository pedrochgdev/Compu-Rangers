package com.compurangers.platform.service.user.auth;

import com.compurangers.platform.core.domain.user.auth.TokenRecuperacion;
import com.compurangers.platform.dao.user.auth.ITokenRecuperacionDAO;
import java.time.LocalDateTime;
import java.util.UUID;

public class TokenRecuperacionBO {
    private final ITokenRecuperacionDAO tokenDAO;
    
     public TokenRecuperacionBO(ITokenRecuperacionDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }
     
    public int addToken(TokenRecuperacion token) {
        return tokenDAO.add(token);
    }
    
    public TokenRecuperacion searchToken(String token){
        return tokenDAO.searchByToken(token);
    }
    
    public void markTokenAsUsed(int id){
        tokenDAO.markTokenAsUsed(id);
    }
    
    public String generateToken(int userId, LocalDateTime fechaExpiracion) {
        String token = UUID.randomUUID().toString();

        TokenRecuperacion nuevoToken = new TokenRecuperacion();
        nuevoToken.setUserId(userId);
        nuevoToken.setToken(token);
        nuevoToken.setFechaExpiracion(fechaExpiracion);

        int result = tokenDAO.add(nuevoToken);

        if (result > 0) {
            return token;
        } else {
            throw new RuntimeException("No se pudo guardar el token de recuperación");
        }
    }
    
}

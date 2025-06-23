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
        final int MAX_INTENTOS = 100;
        int intentos = 0;

        while (intentos < MAX_INTENTOS) {
            String token = UUID.randomUUID().toString();

            TokenRecuperacion nuevoToken = new TokenRecuperacion();
            nuevoToken.setUserId(userId);
            nuevoToken.setToken(token);
            nuevoToken.setFechaExpiracion(fechaExpiracion);

            try {
                int result = tokenDAO.add(nuevoToken);
                if (result > 0) {
                    return token;
                }
            } catch (Exception e) {
                // Si fue por duplicado, reintenta. Si es otra causa, relanza
                if (!e.getMessage().toLowerCase().contains("duplicate")) {
                    throw new RuntimeException("Error al guardar token: " + e.getMessage(), e);
                }
            }

            intentos++;
        }

        throw new RuntimeException("No se pudo generar un token único tras varios intentos");
    }
    
}

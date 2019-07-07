/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.security.jwt;

import com.funDream.security.MainUser;
import io.jsonwebtoken.*;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Component
public class JwtProvider {
    
     private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken(Authentication authentication) {
        MainUser mainUser = (MainUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(mainUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    
    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

   public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("tokenformado " +e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("tokenoportado " +e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("tokenrado " +e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("tokeno " +e.getMessage());
        } catch (SignatureException e) {
            logger.error("errora firma " +e.getMessage());
        }
        return false;
    }
}

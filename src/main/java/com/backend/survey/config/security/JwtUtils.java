package com.backend.survey.config.security;

import com.backend.survey.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${config.jwt.secretKey}")
    public String JWT_SECRETKEY;

    @Value("${config.jwt.expirationTime}")
    public String JWT_EXPIRATIONTIME;

    public String generateToken(Authentication authentication) {
        // Truy xuất thông tin người dùng đang đặng nhập.
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();// xác thực

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRETKEY).compact();
    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRETKEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRETKEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}

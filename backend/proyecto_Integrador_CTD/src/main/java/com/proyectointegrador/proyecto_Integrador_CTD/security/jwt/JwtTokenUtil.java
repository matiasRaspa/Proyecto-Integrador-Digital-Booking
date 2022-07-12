package com.proyectointegrador.proyecto_Integrador_CTD.security.jwt;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.UserDto;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Autowired
    private UserService userService;

    private String SECRET_KEY = "anitaPasion";

    public String extractUserName(String token) {
        return extractClaimUsername(token);
    }

    public Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    public String extractClaimUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new java.util.HashMap<>();
        UserDto user = userService.findByEmail(userDetails.getUsername());
        return createToken(claims, userDetails.getUsername(), user.getName() + " " + user.getLastName(), user.getId(), String.valueOf(user.getRole().getName()));
    }

    private String createToken(Map<String, Object> claims, String subject, String fullName, Long id, String role) {
        return Jwts.builder().setClaims(claims).setIssuer(role).setSubject(subject).setId(String.valueOf(id)).setAudience(fullName).setIssuedAt(new Date(System.currentTimeMillis()))
                //Here we set the expiration time of the token to 24 hours ( 3.600.000 milliseconds * 24 = 86.400.000 = 24 hours 86.400.000)
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 24 * 60 * 60)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}

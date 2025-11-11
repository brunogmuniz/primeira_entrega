package br.csi.trilhagaucha.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.security.Key;

@Component
public class JwtUtil {

    @Value("LdWMUun13JHSResygGny8SLxyeahN3ZmpBsjvwXeilQ")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    public String gerarToken(String username) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
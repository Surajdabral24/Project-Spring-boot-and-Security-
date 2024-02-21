package com.example.peter.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtService {

    public static final String SECRET = "ddcfe2d3b38db36b9cf721734fa84cc1aff4cfec41037736e418df7ecf8022e5";

    public static String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    private static String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    private static Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);

    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isAccountNonExpired(token);
    }

    private boolean isAccountNonExpired(String token) {
        return extractExpiratiion(token).before(new Date());
    }

    private Date extractExpiratiion(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T>T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .getSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }


    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

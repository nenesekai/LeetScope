package nenesekai.leetscope.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("OttoHandsome114514MaimaiDXFestivalPlusWDNMD3141592653HatsuneMiku".getBytes());

    public static String generateToken(String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 600 * 1000L); // expire in 10 minutes
        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String parseToken(String jws) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(jws)
                .getPayload()
                .getSubject();
    }
}

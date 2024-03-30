package Final.Project.dodo.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.authKey}")
    private String authKey;

    public String generateToken(Long userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiration);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, authKey)
                .claim("userId", userId)
                .compact();
    }
    public String generateAccessToken(Long userId) {
        return generateToken(userId);
    }

    public Long validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(authKey).parseClaimsJws(token).getBody();
            // Validate other aspects of the token (expiration, etc.) if necessary
            if (claims != null) {
                return Long.parseLong(claims.get("userId").toString());
            } else {
                throw new RuntimeException("Invalid token: Empty claims");
            }
        } catch (SignatureException e) {
        // Log the exception details
        log.error("JWT signature mismatch error occurred: {}", e.getMessage(), e);
        // Throw a more informative exception message
        throw new RuntimeException("JWT signature mismatch error occurred: " + e.getMessage(), e);
    } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expired. Please log in again.");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Malformed token.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate token: " + e.getMessage());
        }


    }

    public String getClaim(String token) {
        try {
            int i = token.lastIndexOf('.');
            String withoutSignature = token.substring(0, i + 1);

            Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
            Claims claims = untrusted.getBody();

            if (claims != null) {
                return String.valueOf(claims.get("claim"));
            } else {
                throw new RuntimeException("Token is empty");
            }
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expired. Please log in again.");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Malformed token.");
        } catch (ResponseStatusException e) {
            throw new RuntimeException("Token is empty");
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve claim: " + e.getMessage());
        }
    }
}

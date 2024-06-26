package Final.Project.dodo.utils;

import Final.Project.dodo.exception.AuthException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.authKey}")
    private String authKey;

    public String generateToken(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusMinutes(expiration);

        Date issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, authKey)
                .claim("userId", userId)
                .compact();
    }
    public String generateAccessToken(Long userId) {
        return generateToken(userId);
    }

    public Long validateToken(String token, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        try {
            Claims claims = Jwts.parser().setSigningKey(authKey).parseClaimsJws(token).getBody();
            if (claims != null) {
                return Long.parseLong(claims.get("userId").toString());
            } else {
                throw new AuthException(ResourceBundleLanguage.periodMessage(language,""));
            }
        } catch (SignatureException e) {
        throw new AuthException("Invalid token");
    } catch (ExpiredJwtException e) {
            throw new AuthException("Token expired. Please log in again.");
        } catch (MalformedJwtException e) {
            throw new AuthException("Malformed token.");
        } catch (Exception e) {
            throw new AuthException("Failed to validate token: " + e.getMessage());
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

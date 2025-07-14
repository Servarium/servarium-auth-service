package app.servarium.adapter.security;

import app.servarium.domain.model.User;
import app.servarium.domain.port.output.security.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JjwtAdapter implements TokenProvider {
    private final JwtProperties jwtProperties;

    private PrivateKey refreshPrivateKey;
    private PublicKey refreshPublicKey;

    private PrivateKey accessPrivateKey;

    @PostConstruct
    private void init() {
        this.refreshPrivateKey = RsaUtils.generatePrivateKey(jwtProperties.refreshPrivateKey());
        this.refreshPublicKey = RsaUtils.generatePublicKey(jwtProperties.refreshPublicKey());

        this.accessPrivateKey = RsaUtils.generatePrivateKey(jwtProperties.accessPrivateKey());
    }

    @Override
    public String generateAccessToken(User user) {
        return Jwts
                .builder()
                .signWith(accessPrivateKey, Jwts.SIG.RS256)
                .subject(user.getId().toString())
                .claim("role", user.getRole().getName())
                .issuedAt(Date.from(Instant.now()))
                .expiration(calcExpiration(jwtProperties.accessDuration()))
                .compact();
    }

    @Override
    public String generateRefreshToken(User user) {
        return Jwts
                .builder()
                .signWith(refreshPrivateKey, Jwts.SIG.RS256)
                .subject(user.getId().toString())
                .issuedAt(Date.from(Instant.now()))
                .expiration(calcExpiration(jwtProperties.refreshDuration()))
                .compact();
    }

    private Date calcExpiration(Duration duration) {
        return Date.from(Instant.now().plus(duration));
    }

    @Override
    public boolean isRefreshTokenValid(String token) {
        try {
            parseToken(token, refreshPublicKey);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private void parseToken(String token, PublicKey publicKey) {
        Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token);
    }

    @Override
    public long getRefreshTokenSubject(String token) {
        String sub = extractClaims(token, refreshPublicKey, Claims::getSubject);
        return Long.parseLong(sub);
    }

    @Override
    public Instant getRefreshTokenExpiresAt(String token) {

        return extractClaims(token, refreshPublicKey, Claims::getExpiration).toInstant();
    }

    private <T> T extractClaims(String token, PublicKey key, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token, key);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, PublicKey publicKey) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
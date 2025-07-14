package app.servarium.domain.port.output.security;

import app.servarium.domain.model.User;

import java.time.Instant;

public interface TokenProvider {
    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    boolean isRefreshTokenValid(String token);

    long getRefreshTokenSubject(String token);

    Instant getRefreshTokenExpiresAt(String token);
}

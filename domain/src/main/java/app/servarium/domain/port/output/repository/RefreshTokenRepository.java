package app.servarium.domain.port.output.repository;

import app.servarium.domain.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> find(long userId, String clientId);

    void save(RefreshToken refreshToken);

    void delete(long userId, String clientId);

    boolean exists(long userId, String clientId);
}

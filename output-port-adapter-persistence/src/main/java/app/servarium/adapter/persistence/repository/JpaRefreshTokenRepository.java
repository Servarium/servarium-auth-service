package app.servarium.adapter.persistence.repository;

import app.servarium.adapter.persistence.entity.JpaRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRefreshTokenRepository extends JpaRepository<JpaRefreshToken, Long> {
    Optional<JpaRefreshToken> findByUserIdAndClientId(long userId, String clientId);

    void removeByUserIdAndClientId(long userId, String clientId);

    boolean existsByUserIdAndClientId(long userId, String clientId);
}

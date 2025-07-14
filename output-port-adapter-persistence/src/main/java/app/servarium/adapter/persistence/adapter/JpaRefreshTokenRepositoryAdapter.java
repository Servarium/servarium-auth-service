package app.servarium.adapter.persistence.adapter;

import app.servarium.adapter.persistence.adapter.mapper.JpaRefreshTokenMapper;
import app.servarium.adapter.persistence.repository.JpaRefreshTokenRepository;
import app.servarium.domain.model.RefreshToken;
import app.servarium.domain.port.output.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRefreshTokenRepositoryAdapter implements RefreshTokenRepository {
    private final JpaRefreshTokenRepository repository;

    private final JpaRefreshTokenMapper mapper;

    @Override
    public Optional<RefreshToken> find(long userId, String clientId) {
        return repository.findByUserIdAndClientId(userId, clientId)
                .map(mapper::toDomain);
    }

    @Override
    public void save(RefreshToken refreshToken) {
        repository.save(mapper.toEntity(refreshToken));
    }

    @Override
    public void delete(long userId, String clientId) {
        repository.removeByUserIdAndClientId(userId, clientId);
    }

    @Override
    public boolean exists(long userId, String clientId) {
        return repository.existsByUserIdAndClientId(userId, clientId);
    }
}

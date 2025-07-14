package app.servarium.adapter.persistence.adapter;

import app.servarium.adapter.persistence.adapter.mapper.JpaUserMapper;
import app.servarium.adapter.persistence.repository.JpaUserRepository;
import app.servarium.domain.model.User;
import app.servarium.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository repository;

    private final JpaUserMapper mapper;

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public long save(User user) {
        return repository.save(mapper.toEntity(user))
                .getId();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }
}

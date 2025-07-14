package app.servarium.adapter.persistence.adapter;

import app.servarium.adapter.persistence.adapter.mapper.JpaRoleMapper;
import app.servarium.adapter.persistence.repository.JpaRoleRepository;
import app.servarium.domain.model.Role;
import app.servarium.domain.port.output.repository.RoleRepository;
import app.servarium.domain.shared.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRoleRepositoryAdapter implements RoleRepository {
    private final JpaRoleRepository repository;

    private final JpaRoleMapper mapper;

    @Override
    public Optional<Role> find(Roles role) {
        return repository.findByName(role.getName())
                .map(mapper::toDomain);
    }
}

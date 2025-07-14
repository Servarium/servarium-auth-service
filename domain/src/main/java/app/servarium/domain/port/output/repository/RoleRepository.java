package app.servarium.domain.port.output.repository;

import app.servarium.domain.model.Role;
import app.servarium.domain.shared.Roles;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> find(Roles role);
}

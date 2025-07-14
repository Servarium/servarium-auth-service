package app.servarium.domain.shared.exception;

import app.servarium.domain.shared.Roles;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Roles role) {
        super("Role with name=%s not found".formatted(role.getName()));
    }
}

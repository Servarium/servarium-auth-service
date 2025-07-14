package app.servarium.domain.shared;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Roles {
    USER("ROLE_USER");

    private final String name;
}

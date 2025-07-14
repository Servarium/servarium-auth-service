package app.servarium.core.usecase;

import app.servarium.domain.model.Role;
import app.servarium.domain.model.User;
import app.servarium.domain.port.input.RegisterInputPort;
import app.servarium.domain.port.output.repository.RoleRepository;
import app.servarium.domain.port.output.repository.UserRepository;
import app.servarium.domain.port.output.security.PasswordEncoder;
import app.servarium.domain.shared.Roles;
import app.servarium.domain.shared.exception.RoleNotFoundException;
import app.servarium.domain.shared.exception.UserAlreadyExistsException;
import app.servarium.domain.shared.params.RegisterParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUseCase implements RegisterInputPort {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public long execute(RegisterParams params) {
        final String email = params.getEmail();
        log.info("Registration attempt - email={}", email);

        if (userRepository.existByEmail(email)) {
            log.warn("User with email={} already exists", email);
            throw new UserAlreadyExistsException(email);
        }

        final String passHash = passwordEncoder.encode(params.getPassword());
        final Role roleUser = findRoleUser();

        User newUser = User.builder()
                .email(email)
                .password(passHash)
                .role(roleUser)
                .build();

        long newUserId = userRepository.save(newUser);

        log.info("Successful registration - User: {}", newUserId);
        return newUserId;
    }

    private Role findRoleUser() {
        return roleRepository.find(Roles.USER)
                .orElseThrow(() -> {
                    log.error("Role not found: {}", Roles.USER);
                    return new RoleNotFoundException(Roles.USER);
                });
    }
}

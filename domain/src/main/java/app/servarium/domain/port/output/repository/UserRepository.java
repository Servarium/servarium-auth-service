package app.servarium.domain.port.output.repository;

import app.servarium.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    boolean existByEmail(String email);

    long save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);
}

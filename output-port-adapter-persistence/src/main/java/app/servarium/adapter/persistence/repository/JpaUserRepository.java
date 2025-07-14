package app.servarium.adapter.persistence.repository;

import app.servarium.adapter.persistence.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {

    Optional<JpaUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
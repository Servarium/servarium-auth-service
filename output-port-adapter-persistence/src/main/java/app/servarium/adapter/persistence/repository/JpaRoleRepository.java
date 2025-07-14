package app.servarium.adapter.persistence.repository;

import app.servarium.adapter.persistence.entity.JpaRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<JpaRole, Long> {
    Optional<JpaRole> findByName(String name);
}
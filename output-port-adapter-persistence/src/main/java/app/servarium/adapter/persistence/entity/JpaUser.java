package app.servarium.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class JpaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private JpaRole role;

    @Column(name = "email", length = 256, nullable = false, unique = true)
    private String email;

    @Column(name = "pass_hash", columnDefinition = "TEXT", nullable = false)
    private String passHash;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}

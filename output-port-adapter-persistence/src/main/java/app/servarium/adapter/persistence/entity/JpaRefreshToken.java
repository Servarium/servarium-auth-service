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
@Table(
        name = "refresh_tokens",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "client_id"})
)
public class JpaRefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private JpaUser user;

    @Column(name = "token_hash", columnDefinition = "TEXT", nullable = false)
    private String tokenHash;

    @Column(name = "client_id", columnDefinition = "TEXT", nullable = false)
    private String clientId;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;
}
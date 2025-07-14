package app.servarium.domain.model;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RefreshToken {
    @EqualsAndHashCode.Include
    private Long id;

    private User user;

    private String tokenHash;

    private String clientId;

    private Instant expiresAt;
}

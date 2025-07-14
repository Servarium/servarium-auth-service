package app.servarium.adapter.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
        String accessPrivateKey,
        String accessPublicKey,
        Duration accessDuration,

        String refreshPrivateKey,
        String refreshPublicKey,
        Duration refreshDuration
) {
}
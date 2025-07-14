package app.servarium.domain.shared.exception;

public class AuthenticationException extends RuntimeException {
    private AuthenticationException(String message) {
        super(message);
    }

    public static AuthenticationException invalidRefreshToken(String clientId) {
        return new AuthenticationException("Refresh token is invalid for clientId=%s".formatted(clientId));
    }

    public static AuthenticationException revokedRefreshToken(long userId, String clientId) {
        return new AuthenticationException("Refresh token has been revoked for user with id=%d and clientId=%s"
                .formatted(userId, clientId));
    }
}
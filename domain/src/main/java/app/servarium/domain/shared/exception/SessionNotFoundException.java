package app.servarium.domain.shared.exception;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(long userId, String clientId) {
        super("Session for user=%d and client=%s not found".formatted(userId, clientId));
    }
}

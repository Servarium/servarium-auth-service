package app.servarium.domain.shared.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("User with email=%s already exists".formatted(email));
    }
}

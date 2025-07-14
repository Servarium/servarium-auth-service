package app.servarium.domain.shared.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String email) {
        super("Wrong password for user with email=%s".formatted(email));
    }
}

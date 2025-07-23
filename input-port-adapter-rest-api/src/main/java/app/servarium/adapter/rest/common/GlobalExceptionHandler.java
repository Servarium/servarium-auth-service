package app.servarium.adapter.rest.common;

import app.servarium.adapter.rest.dto.response.ErrorResponse;
import app.servarium.domain.shared.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ResponseEntityFactory responseFactory;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e, WebRequest r) {
        List<ConstraintViolation> violations = e.getFieldErrors()
                .stream()
                .map(error ->
                        ConstraintViolation.of(error.getField(), error.getDefaultMessage()
                ))
                .toList();

        return responseFactory.create(new ErrorParams("a001", violations, HttpStatus.BAD_REQUEST, r));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handle(AuthenticationException e, WebRequest r) {
        return responseFactory.create(new ErrorParams("a010", e.getMessage(), HttpStatus.UNAUTHORIZED, r));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> handle(WrongPasswordException e, WebRequest r) {
        return responseFactory.create(new ErrorParams("a011", e.getMessage(), HttpStatus.UNAUTHORIZED, r));
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(SessionNotFoundException e, WebRequest r) {
        return responseFactory.create(new ErrorParams("a012", e.getMessage(), HttpStatus.NOT_FOUND, r));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handle(UserAlreadyExistsException e, WebRequest r) {
        return responseFactory.create(new ErrorParams("a020", e.getMessage(), HttpStatus.CONFLICT, r));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException e, WebRequest r) {
        return responseFactory.create(new ErrorParams("a021", e.getMessage(), HttpStatus.NOT_FOUND, r));
    }
}

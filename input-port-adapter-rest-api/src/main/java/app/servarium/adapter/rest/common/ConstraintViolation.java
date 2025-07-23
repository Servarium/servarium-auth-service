package app.servarium.adapter.rest.common;

import lombok.Value;

@Value(staticConstructor = "of")
public class ConstraintViolation {
    String field;

    String message;
}

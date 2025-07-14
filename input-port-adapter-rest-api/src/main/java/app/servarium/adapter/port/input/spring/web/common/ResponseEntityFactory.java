package app.servarium.adapter.port.input.spring.web.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseEntityFactory {
    private final ObjectMapper objectMapper;
    private final MessageSource messageSource;

    public ResponseEntity<ErrorResponse> create(ErrorParams em) {
        return ResponseEntity
                .status(em.status().value())
                .body(new ErrorResponse(
                        messageSource.getMessage(em.code(), null, em.request().getLocale()),
                        em.code(),
                        em.status().value(),
                        em.detail()));
    }
}

package app.servarium.adapter.port.input.spring.web.common;

public record ErrorResponse (
        String title,
        String code,
        int status,
        Object detail
) {}

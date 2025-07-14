package app.servarium.domain.port.output.security;

interface HashEncoder {
    String encode(String value);

    boolean isMatched(String value, String encodedValue);
}

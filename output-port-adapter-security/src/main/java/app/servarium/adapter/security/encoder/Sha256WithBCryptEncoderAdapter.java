package app.servarium.adapter.security.encoder;

import app.servarium.domain.port.output.security.TokenEncoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
@RequiredArgsConstructor
public class Sha256WithBCryptEncoderAdapter implements TokenEncoder {
    private final org.springframework.security.crypto.password.PasswordEncoder bcrypt;

    @Override
    public String encode(String value) {
        String sha256Hex = sha256Hex(value);
        return bcrypt.encode(sha256Hex);
    }

    @Override
    public boolean isMatched(String value, String encodedValue) {
        String sha256Hex = sha256Hex(value);
        return bcrypt.matches(sha256Hex, encodedValue);
    }

    @SneakyThrows
    private String sha256Hex(String input) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hex = new StringBuilder();
        for (byte b : hashBytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}

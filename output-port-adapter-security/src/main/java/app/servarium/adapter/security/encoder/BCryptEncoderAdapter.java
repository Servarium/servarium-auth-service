package app.servarium.adapter.security.encoder;

import app.servarium.domain.port.output.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptEncoderAdapter implements PasswordEncoder {
    private final org.springframework.security.crypto.password.PasswordEncoder bcrypt;

    @Override
    public String encode(String value) {
        return bcrypt.encode(value);
    }

    @Override
    public boolean isMatched(String value, String encodedValue) {
        return bcrypt.matches(value, encodedValue);
    }
}

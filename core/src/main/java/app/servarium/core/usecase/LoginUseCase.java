package app.servarium.core.usecase;

import app.servarium.domain.model.RefreshToken;
import app.servarium.domain.model.User;
import app.servarium.domain.port.input.LoginInputPort;
import app.servarium.domain.port.output.repository.RefreshTokenRepository;
import app.servarium.domain.port.output.repository.UserRepository;
import app.servarium.domain.port.output.security.PasswordEncoder;
import app.servarium.domain.port.output.security.TokenEncoder;
import app.servarium.domain.port.output.security.TokenProvider;
import app.servarium.domain.shared.exception.UserNotFoundException;
import app.servarium.domain.shared.exception.WrongPasswordException;
import app.servarium.domain.shared.params.LoginParams;
import app.servarium.domain.shared.result.TokensData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginUseCase implements LoginInputPort {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final TokenEncoder tokenEncoder;

    @Override
    @Transactional
    public TokensData execute(LoginParams params) {
        String email = params.getEmail();
        String clientId = params.getClientId();
        log.info("Login attempt - email={}, Client: {}", email, clientId);

        User user = findUserByEmail(email);

        if (!passwordEncoder.isMatched(params.getPassword(), user.getPassword())) {
            log.warn("Wrong password - User: email={}", email);
            throw new WrongPasswordException(email);
        }

        String newRefreshToken = tokenProvider.generateRefreshToken(user);
        String newAccessToken = tokenProvider.generateAccessToken(user);
        log.debug("Tokens generated - User: email={}", email);

        updateOrSaveRefreshToken(user, clientId, newRefreshToken);

        log.info("Login successful - User: email={}", email);
        return TokensData.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("User not found for email={}", email);
                    return new UserNotFoundException(email);
                });
    }

    private void updateOrSaveRefreshToken(User user, String clientId, String refreshToken) {
        RefreshToken newRefreshToken = RefreshToken.builder()
                .user(user)
                .clientId(clientId)
                .tokenHash(tokenEncoder.encode(refreshToken))
                .expiresAt(tokenProvider.getRefreshTokenExpiresAt(refreshToken))
                .build();

        refreshTokenRepository.find(user.getId(), clientId)
                .ifPresent(saved -> {
                    log.debug("Existing refresh token found - User: email={}, Client: {}", user.getEmail(), clientId);
                    newRefreshToken.setId(saved.getId());
                });

        refreshTokenRepository.save(newRefreshToken);
        log.debug("New refresh token saved - User: email={}, Client: {}", user.getEmail(), clientId);
    }
}

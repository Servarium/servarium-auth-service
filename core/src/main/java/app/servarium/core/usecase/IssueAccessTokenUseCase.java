package app.servarium.core.usecase;

import app.servarium.domain.model.RefreshToken;
import app.servarium.domain.model.User;
import app.servarium.domain.port.input.IssueAccessTokenInputPort;
import app.servarium.domain.port.output.repository.RefreshTokenRepository;
import app.servarium.domain.port.output.security.TokenEncoder;
import app.servarium.domain.port.output.security.TokenProvider;
import app.servarium.domain.shared.exception.AuthenticationException;
import app.servarium.domain.shared.params.IssueAccessTokenParams;
import app.servarium.domain.shared.result.AccessTokenData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueAccessTokenUseCase implements IssueAccessTokenInputPort {
    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenProvider tokenProvider;
    private final TokenEncoder tokenEncoder;

    @Override
    @Transactional
    public AccessTokenData execute(IssueAccessTokenParams params) {
        String refreshToken = params.getRefreshToken();
        String clientId = params.getClientId();
        log.info("Attempting to issue access token - client: {}", clientId);

        RefreshToken savedRefreshToken = findSavedRefreshToken(refreshToken, clientId);

        validateRefreshToken(refreshToken, savedRefreshToken);

        User user = savedRefreshToken.getUser();
        String newAccessToken = tokenProvider.generateAccessToken(user);

        log.info("Access token issue is successful - User: {}, client: {}", user.getId(), clientId);
        return AccessTokenData.of(newAccessToken);
    }

    private RefreshToken findSavedRefreshToken(String refreshToken, String clientId) {
        if (!tokenProvider.isRefreshTokenValid(refreshToken)) {
            log.warn("Invalid refresh token received - client: {}", clientId);
            throw AuthenticationException.invalidRefreshToken(clientId);
        }

        long userId = tokenProvider.getRefreshTokenSubject(refreshToken);
        log.debug("Extracted userId={} from refresh token", userId);

        return refreshTokenRepository.find(userId, clientId)
                .orElseThrow(() -> {
                    log.warn("Refresh token has been revoked - User: {}, client: {}", userId, clientId);
                    return AuthenticationException.revokedRefreshToken(userId, clientId);
                });
    }

    private void validateRefreshToken(String refreshToken, RefreshToken savedRefreshToken) {
        long userId = savedRefreshToken.getUser().getId();
        String clientId = savedRefreshToken.getClientId();

        if (!tokenEncoder.isMatched(refreshToken, savedRefreshToken.getTokenHash())) {
            log.warn("Refresh token validation error - User: {}, client: {}", userId, clientId);
            throw AuthenticationException.revokedRefreshToken(userId, clientId);
        }

        log.debug("Refresh token successfully validated - User: {}, client: {}", userId, clientId);
    }
}

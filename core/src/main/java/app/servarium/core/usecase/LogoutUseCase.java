package app.servarium.core.usecase;

import app.servarium.domain.port.output.repository.RefreshTokenRepository;
import app.servarium.domain.shared.exception.SessionNotFoundException;
import app.servarium.domain.shared.params.LogoutParams;
import app.servarium.domain.port.input.LogoutInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogoutUseCase implements LogoutInputPort {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void execute(LogoutParams params) {
        long userId = params.getUserId();
        String clientId = params.getClientId();
        log.info("User logout initiated - User: {}, Client: {}", userId, clientId);

        checkSessionExists(userId, clientId);

        refreshTokenRepository.delete(params.getUserId(), params.getClientId());
        log.info("Successful logout - User: {}, Client: {}", userId, clientId);
    }

    private void checkSessionExists(long userId, String clientId) {
        if(!refreshTokenRepository.exists(userId, clientId)) {
            log.warn("Session not found - User: {}, client: {}", userId, clientId);
            throw new SessionNotFoundException(userId, clientId);
        }
    }
}

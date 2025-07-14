package app.servarium.adapter.port.input.spring.web;

import app.servarium.adapter.port.input.spring.web.request.IssueAccessTokenRequest;
import app.servarium.adapter.port.input.spring.web.request.LoginRequest;
import app.servarium.adapter.port.input.spring.web.request.LogoutRequest;
import app.servarium.adapter.port.input.spring.web.request.RegisterRequest;
import app.servarium.domain.port.input.IssueAccessTokenInputPort;
import app.servarium.domain.port.input.LoginInputPort;
import app.servarium.domain.port.input.LogoutInputPort;
import app.servarium.domain.port.input.RegisterInputPort;
import app.servarium.domain.shared.params.IssueAccessTokenParams;
import app.servarium.domain.shared.params.LoginParams;
import app.servarium.domain.shared.params.LogoutParams;
import app.servarium.domain.shared.params.RegisterParams;
import app.servarium.domain.shared.result.AccessTokenData;
import app.servarium.domain.shared.result.TokensData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestController implements AuthRestApi {
    private final RegisterInputPort registerInputPort;
    private final LoginInputPort loginInputPort;
    private final IssueAccessTokenInputPort issueAccessTokenInputPort;
    private final LogoutInputPort logoutInputPort;

    @Override
    public ResponseEntity<Void> register(RegisterRequest request) {
        registerInputPort.execute(RegisterParams.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TokensData> login(LoginRequest request) {
        var result = loginInputPort.execute(LoginParams.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .clientId(request.getClientId())
                .build());

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<AccessTokenData> issueAccessToken(IssueAccessTokenRequest request) {
        var result = issueAccessTokenInputPort.execute(IssueAccessTokenParams.builder()
                .refreshToken(request.getRefreshToken())
                .clientId(request.getClientId())
                .build());

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> logout(LogoutRequest request) {
        logoutInputPort.execute(LogoutParams.builder()
                .userId(request.getUserId())
                .clientId(request.getClientId())
                .build());

        return ResponseEntity.ok().build();
    }
}

package app.servarium.adapter.rest;

import app.servarium.adapter.rest.dto.request.IssueAccessTokenRequest;
import app.servarium.adapter.rest.dto.request.LoginRequest;
import app.servarium.adapter.rest.dto.request.LogoutRequest;
import app.servarium.adapter.rest.dto.request.RegisterRequest;
import app.servarium.adapter.rest.dto.response.TokensResponse;
import app.servarium.domain.port.input.IssueAccessTokenInputPort;
import app.servarium.domain.port.input.LoginInputPort;
import app.servarium.domain.port.input.LogoutInputPort;
import app.servarium.domain.port.input.RegisterInputPort;
import app.servarium.domain.shared.params.IssueAccessTokenParams;
import app.servarium.domain.shared.params.LoginParams;
import app.servarium.domain.shared.params.LogoutParams;
import app.servarium.domain.shared.params.RegisterParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<TokensResponse> login(LoginRequest request) {
        var result = loginInputPort.execute(LoginParams.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .clientId(request.getClientId())
                .build());

        return ResponseEntity.ok(new TokensResponse(result.getRefreshToken(), result.getAccessToken()));
    }

    @Override
    public ResponseEntity<String> issueAccessToken(IssueAccessTokenRequest request) {
        var result = issueAccessTokenInputPort.execute(IssueAccessTokenParams.builder()
                .refreshToken(request.getRefreshToken())
                .clientId(request.getClientId())
                .build());

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> logout(@AuthenticationPrincipal long userId, LogoutRequest request) {
        logoutInputPort.execute(LogoutParams.builder()
                .userId(userId)
                .clientId(request.getClientId())
                .build());

        return ResponseEntity.ok().build();
    }
}
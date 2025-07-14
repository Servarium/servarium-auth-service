package app.servarium.adapter.port.input.spring.web;

import app.servarium.adapter.port.input.spring.web.request.IssueAccessTokenRequest;
import app.servarium.adapter.port.input.spring.web.request.LoginRequest;
import app.servarium.adapter.port.input.spring.web.request.LogoutRequest;
import app.servarium.adapter.port.input.spring.web.request.RegisterRequest;
import app.servarium.domain.shared.result.AccessTokenData;
import app.servarium.domain.shared.result.TokensData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Аутентификация", description = "Операции для регистрации, авторизации и JWT токенов")
@RequestMapping(
        value = "/api/v1/auth",
        produces = "application/json"
)
public interface AuthRestApi {

    @Operation(
            summary = "Регистрация нового пользователя",
            description = "Создаёт нового пользователя с указанными данными",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная регистрация"),
                    @ApiResponse(responseCode = "400", description = "a001 - Неверные данные запроса"),
                    @ApiResponse(responseCode = "409", description = "a020 - Пользователь существует")
            }
    )
    @PostMapping(value = "/register")
    ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request);

    @Operation(
            summary = "Вход пользователя",
            description = "Аутентифицирует пользователя и возвращает пару JWT токенов",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный вход",
                            content = @Content(schema = @Schema(implementation = TokensData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "a001 - Неверные данные запроса", content = @Content),
                    @ApiResponse(responseCode = "401", description = "a011 - Неверный пароль", content = @Content),
                    @ApiResponse(responseCode = "404", description = "a021 - Пользователь не найден", content = @Content)
            }
    )
    @PostMapping(value = "/login")
    ResponseEntity<TokensData> login(@Valid @RequestBody LoginRequest request);

    @Operation(
            summary = "Получение нового JWT токена доступа",
            description = "Получение нового JWT токена доступа токена, используя JWT refresh токен",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Новый JWT access токен",
                            content = @Content(schema = @Schema(implementation = AccessTokenData.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "a001 - Неверные данные запроса", content = @Content),
                    @ApiResponse(responseCode = "401", description = "a010 - Ошибка аутентификации", content = @Content)
            }
    )
    @PostMapping(value = "/token")
    ResponseEntity<AccessTokenData> issueAccessToken(@Valid @RequestBody IssueAccessTokenRequest request);

    @Operation(
            summary = "Выход пользователя",
            description = "Аннулирует JWT refresh токен для сессии с указанным устройством",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный выход"),
                    @ApiResponse(responseCode = "400", description = "a001 - Неверные данные запроса"),
                    @ApiResponse(responseCode = "404", description = "a012 - Сессия не найдена"),
            }
    )
    @PostMapping(value = "/logout")
    ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest request);
}

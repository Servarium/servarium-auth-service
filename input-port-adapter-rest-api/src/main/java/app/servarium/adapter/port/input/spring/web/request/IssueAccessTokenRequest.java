package app.servarium.adapter.port.input.spring.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на получение нового JWT access токена")
public class IssueAccessTokenRequest {
    @NotBlank
    @Schema(description = "JWT refresh токен", example = "dGhpc0lzQVRlc3RSZWZyZXNoVG9rZW4=")
    private String refreshToken;

    @NotBlank
    @Schema(description = "Идентификатор устройства клиента", example = "client-12345")
    private String clientId;
}

package app.servarium.adapter.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на вход пользователя в систему")
public class LoginRequest {
    @Email
    @NotBlank
    @Size(max = 64)
    @Schema(description = "Email пользователя", example = "john.doe@example.com")
    private String email;

    @NotBlank
    @Size(min = 8, max = 32)
    @Schema(description = "Пароль пользователя", example = "StrongP@ssw0rd")
    private String password;

    @NotBlank
    @Schema(description = "Идентификатор устройства", example = "client-12345")
    private String clientId;
}

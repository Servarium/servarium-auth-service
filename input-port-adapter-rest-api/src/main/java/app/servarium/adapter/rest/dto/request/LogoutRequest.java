package app.servarium.adapter.rest.dto.request;

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
@Schema(description = "Запрос на выход пользователя из системы")
public class LogoutRequest {

    @NotBlank
    @Schema(description = "Идентификатор устройства", example = "client-12345")
    private String clientId;
}

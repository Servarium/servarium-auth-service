package app.servarium.adapter.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Jwt refresh и access токены")
public record TokensResponse(
        @Schema(description = "Jwt refresh токен",example = "dGhpc0lzQVRlc3RSZWZyZXNoVG9rZW4=")
        String refreshToken,

        @Schema(description = "Jwt access токен", example = "lOihR0lzOTUlC3tyZBZyhJornDHCjKC8=")
        String accessToken
) {
}
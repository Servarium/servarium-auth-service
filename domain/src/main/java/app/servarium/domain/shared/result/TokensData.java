package app.servarium.domain.shared.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokensData {
    private String accessToken;

    private String refreshToken;
}

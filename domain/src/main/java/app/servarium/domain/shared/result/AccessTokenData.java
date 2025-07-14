package app.servarium.domain.shared.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class AccessTokenData {
    private String accessToken;
}

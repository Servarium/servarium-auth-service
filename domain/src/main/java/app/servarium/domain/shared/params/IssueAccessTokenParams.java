package app.servarium.domain.shared.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueAccessTokenParams {
    private String refreshToken;

    private String clientId;
}

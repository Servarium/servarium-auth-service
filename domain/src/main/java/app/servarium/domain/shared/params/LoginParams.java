package app.servarium.domain.shared.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginParams {
    private String email;

    private String password;

    private String clientId;
}

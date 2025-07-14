package app.servarium.domain.port.input;

import app.servarium.domain.shared.params.LoginParams;
import app.servarium.domain.shared.result.TokensData;

public interface LoginInputPort {
    TokensData execute(LoginParams params);
}

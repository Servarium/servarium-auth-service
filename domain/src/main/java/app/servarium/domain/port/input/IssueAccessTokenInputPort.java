package app.servarium.domain.port.input;

import app.servarium.domain.shared.params.IssueAccessTokenParams;
import app.servarium.domain.shared.result.AccessTokenData;

public interface IssueAccessTokenInputPort {
    AccessTokenData execute(IssueAccessTokenParams params);
}

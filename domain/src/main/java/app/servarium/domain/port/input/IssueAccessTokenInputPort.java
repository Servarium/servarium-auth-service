package app.servarium.domain.port.input;

import app.servarium.domain.shared.params.IssueAccessTokenParams;

public interface IssueAccessTokenInputPort {
    String execute(IssueAccessTokenParams params);
}

package app.servarium.domain.port.input;

import app.servarium.domain.shared.params.LogoutParams;

public interface LogoutInputPort {
    void execute(LogoutParams params);
}
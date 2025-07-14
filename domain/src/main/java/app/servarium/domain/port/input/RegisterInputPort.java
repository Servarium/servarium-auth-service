package app.servarium.domain.port.input;

import app.servarium.domain.shared.params.RegisterParams;

public interface RegisterInputPort {
    long execute(RegisterParams params);
}

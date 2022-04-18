package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;

public abstract class AbstractCommand {
    private final String commandDescription;
    private final boolean isServerCommand;

    AbstractCommand(String commandDescription, boolean isServerCommand) {
        this.commandDescription = commandDescription;
        this.isServerCommand = isServerCommand;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public boolean isServerCommand() {
        return isServerCommand;
    }

    public abstract Response execute(Request request);
}

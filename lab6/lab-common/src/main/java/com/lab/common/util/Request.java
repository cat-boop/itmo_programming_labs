package com.lab.common.util;

import com.lab.common.Data.Route;

import java.io.Serializable;

public class Request implements Serializable {
    private final String commandName;
    private final String commandArgument;
    private final Route routeToSend;

    public Request(String commandName, String commandArgument, Route routeToSend) {
        this.commandName = commandName;
        //TODO argument should not be string
        this.commandArgument = commandArgument;
        this.routeToSend = routeToSend;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandArgument() {
        return commandArgument;
    }

    public Route getRouteToSend() {
        return routeToSend;
    }

    public String toString() {
        return "command name = " + commandName + "; command argument = " + commandArgument + "; route to send = " + routeToSend;
    }
}

package com.lab.common.util;

import com.lab.common.Data.Route;

import java.io.Serializable;

public class Request implements Serializable {
    private final String commandName;
    private Number commandArgument;
    private Route routeToSend;

    public Request(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandArgument(Number commandArgument) {
        this.commandArgument = commandArgument;
    }

    public void setRouteToSend(Route routeToSend) {
        this.routeToSend = routeToSend;
    }

    public String getCommandName() {
        return commandName;
    }

    public Number getCommandArgument() {
        return commandArgument;
    }

    public Route getRouteToSend() {
        return routeToSend;
    }

    public String toString() {
        return "[Имя команды = " + commandName
                + (commandArgument == null ? "" : "; Аргумент команды = " + commandArgument)
                + (routeToSend == null ? "" : "; Путь = " + routeToSend) + "]";
    }
}

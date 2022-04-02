package com.lab.common.util;

import com.lab.common.Data.Route;

import java.util.ArrayList;
import java.util.List;

public class CommandAnalyzer {
    private final String command;
    private String commandName;
    private String commandArgument;
    private Route route;
    private final RouteReader routeReader;
    private final boolean needReadFromScript;
    private final List<String> commands;
    private final List<String> commandsNeedRoute;
    private final List<String> commandsNeedArgument;

    public CommandAnalyzer(String command, RouteReader routeReader, boolean needReadFromScript) {
        this.command = command;
        this.routeReader = routeReader;
        this.needReadFromScript = needReadFromScript;

        commands = new ArrayList<>();
        commands.add("add");
        commands.add("add_if_min");
        commands.add("clear");
        commands.add("count_greater_than_distance");
        commands.add("count_less_than_distance");
        commands.add("help");
        commands.add("info");
        commands.add("max_by_distance");
        commands.add("remove_by_id");
        commands.add("remove_greater");
        commands.add("remove_lower");
        commands.add("show");
        commands.add("update");

        commandsNeedRoute = new ArrayList<>();
        commandsNeedRoute.add("add");
        commandsNeedRoute.add("update");
        commandsNeedRoute.add("add_if_min");
        commandsNeedRoute.add("remove_greater");
        commandsNeedRoute.add("remove_lower");

        commandsNeedArgument = new ArrayList<>();
        commandsNeedArgument.add("update");
        commandsNeedArgument.add("remove_by_id");
        commandsNeedArgument.add("execute_script");
        commandsNeedArgument.add("count_less_than_distance");
        commandsNeedArgument.add("count_greater_than_distance");

        analyzeCommand();
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandArgument() {
        return commandArgument;
    }

    public Route getRoute() {
        return route;
    }

    private void analyzeCommand() {
        String[] inputLineDivided = command.trim().split(" ", 2);
        commandName = inputLineDivided[0].toLowerCase();

        if (!commands.contains(commandName)
                || (commandsNeedArgument.contains(commandName) && inputLineDivided.length == 1)
                || (!commandsNeedArgument.contains(commandName) && inputLineDivided.length == 2)) {
            throw new IllegalStateException("Такой команды не существует");
        }

        if (inputLineDivided.length == 2) {
            commandArgument = inputLineDivided[1];
        }

        if (commandsNeedRoute.contains(commandName)) {
            if (needReadFromScript) {
                route = routeReader.readRouteFromScript();
            } else {
                route = routeReader.readRouteFromConsole();
            }
        }
    }
}

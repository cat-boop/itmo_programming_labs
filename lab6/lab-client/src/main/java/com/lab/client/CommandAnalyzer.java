package com.lab.client;

public class CommandAnalyzer {
    private String commandName;
    private String commandArgument;
    private boolean commandHaveArgument;
    private boolean commandNeedRoute;
    private boolean commandScript;

    public CommandAnalyzer(String command) {
        commandHaveArgument = false;
        commandNeedRoute = false;
        commandScript = false;

        analyzeCommand(command);
    }

    public boolean isCommandScript() {
        return commandScript;
    }

    public boolean isCommandHaveArgument() {
        return commandHaveArgument;
    }

    public boolean isCommandNeedRoute() {
        return commandNeedRoute;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandArgument() {
        return commandArgument;
    }

    public Class<?> getArgumentClass() {
        return ClientCommands.getCommandsNeedArgument().get(commandName);
    }

    private void analyzeCommand(String command) {
        String[] inputLineDivided = command.trim().split(" ", 2);
        commandName = inputLineDivided[0].toLowerCase();

        if (!ClientCommands.getAvailableCommands().contains(commandName)) {
            throw new IllegalStateException("Такой команды не существует");
        }
        if (ClientCommands.getCommandsNeedArgument().containsKey(commandName) && inputLineDivided.length == 1) {
            throw new IllegalStateException("Аргумент не указан");
        }
        if (!ClientCommands.getCommandsNeedArgument().containsKey(commandName) && inputLineDivided.length == 2) {
            throw new IllegalStateException("Аргумент не должен быть указан");
        }

        if ("execute_script".equals(commandName)) {
            commandScript = true;
        }
        if (ClientCommands.getCommandsNeedArgument().containsKey(commandName)) {
            commandHaveArgument = true;
            commandArgument = inputLineDivided[1];
        }
        if (ClientCommands.getCommandsNeedRoute().contains(commandName)) {
            commandNeedRoute = true;
        }
    }
}

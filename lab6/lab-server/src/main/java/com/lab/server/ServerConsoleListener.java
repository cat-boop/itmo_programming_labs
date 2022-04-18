package com.lab.server;

import com.lab.common.util.Request;
import com.lab.common.util.Response;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServerConsoleListener extends Thread {
    private final Scanner scanner;
    private final CommandManager commandManager;
    private final List<String> serverCommands;

    public ServerConsoleListener(CommandManager commandManager, Scanner scanner) {
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.serverCommands = CommandManager.getCommands().entrySet().stream().filter(entry -> entry.getValue().isServerCommand()).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    @Override
    public void run() {
        //TODO когда буду делать nio server надо не делать так
        while (true) {
            try {
                String commandName = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                ServerLogger.logInfoMessage("На сервера введена команда \"{}\"", commandName);
                if (serverCommands.contains(commandName)) {
                    Response executionResult = commandManager.executeCommand(new Request(commandName));

                    System.out.println(executionResult);
                    ServerLogger.logInfoMessage(executionResult.toString());
                } else {
                    String serverAvailableCommands = String.join(", ", serverCommands);
                    System.out.println("Такой команды не существует");
                    System.out.println("Команды, доступные на сервере: {" + serverAvailableCommands + "}");

                    ServerLogger.logInfoMessage("Такой команды не существует");
                    ServerLogger.logInfoMessage("Команды, доступные на сервере: [{}]", serverAvailableCommands);
                }
            } catch (NoSuchElementException e) {
                commandManager.executeCommand(new Request("exit"));
                //TODO когда буду делать nio server надо не делать так
            }
        }
    }
}

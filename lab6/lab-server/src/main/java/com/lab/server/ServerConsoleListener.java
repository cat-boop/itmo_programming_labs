package com.lab.server;

import com.lab.common.util.Request;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerConsoleListener extends Thread {
    private final Scanner scanner;
    private final CommandManager commandManager;
    private final List<String> serverAvailableCommands;
    private final Logger logger;

    public ServerConsoleListener(CommandManager commandManager, Scanner scanner) {
        this.commandManager = commandManager;
        this.scanner = scanner;

        logger = (Logger) LoggerFactory.getLogger(ServerConsoleListener.class);

        serverAvailableCommands = new ArrayList<>();
        serverAvailableCommands.add("save");
        serverAvailableCommands.add("exit");
    }

    @Override
    public void run() {
        //TODO когда буду делать nio server надо не делать так
        while (true) {
            try {
                String commandName = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                logger.info("На сервера введена команда \"{}\"", commandName);
                if ("exit".equals(commandName)) {
                    throw new NoSuchElementException();
                }
                if (serverAvailableCommands.contains(commandName)) {
                    Request request = new Request(commandName);
                    String executionResult = commandManager.executeCommand(request);
                    System.out.println(executionResult);
                    logger.info(executionResult);
                } else {
                    System.out.println("Такой команды не существует");
                    System.out.println("Команды, доступные на сервере: {" + String.join(",", serverAvailableCommands) + "}");
                    logger.info("Такой команды не существует");
                    logger.info("Команды, доступные на сервере: [{}]", String.join(",", serverAvailableCommands));
                }
            } catch (NoSuchElementException e) {
                Request request = new Request("save");
                String executionResult = commandManager.executeCommand(request);
                System.out.println(executionResult);
                System.out.println("Работа сервера остановлена");
                logger.info(executionResult);
                logger.info("Работа сервера остановлена");
                //TODO когда буду делать nio server надо не делать так
                System.exit(0);
            }
        }
    }
}

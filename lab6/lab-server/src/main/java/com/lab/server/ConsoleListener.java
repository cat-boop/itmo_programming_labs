package com.lab.server;

import com.lab.common.util.Request;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleListener extends Thread {
    private static boolean serverShouldWork;
    private final Scanner scanner;
    private final CommandManager commandManager;

    public ConsoleListener(CommandManager commandManager, Scanner scanner) {
        this.commandManager = commandManager;
        this.scanner = scanner;
        serverShouldWork = true;
    }

    @Override
    public void run() {
        while (serverShouldWork) {
            try {
                String commandName = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                //TODO xueta, peredelivay
                Request request = new Request(commandName, "", null);
                System.out.println(commandManager.executeCommand(request));
            } catch (NoSuchElementException e) {
                //System.exit()?
                serverShouldWork = false;
            }
        }
    }

    public static boolean isServerShouldWork() {
        return serverShouldWork;
    }

    public static void setServerShouldWork(boolean serverShouldWork) {
        ConsoleListener.serverShouldWork = serverShouldWork;
    }
}

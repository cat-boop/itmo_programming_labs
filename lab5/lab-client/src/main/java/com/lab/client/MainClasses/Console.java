package com.lab.client.MainClasses;

import com.lab.client.Utility.RouteReader;

import java.util.Scanner;

/**
 * Class that start interactive mode and operates user command
 */
public class Console {
    private final CommandManager commandManager;
    private final Scanner scanner;

    public Console(String fileName) {
        this.scanner = new Scanner(System.in);

        FileManager fileManager = new FileManager(fileName);
        RouteReader routeReader = new RouteReader(scanner);
        this.commandManager = new CommandManager(fileManager, routeReader);
    }

    /**
     * start reading and operates user command until end
     */
    public void startInteractiveMode() {
        System.out.println("Добро пожаловать.");
        System.out.println("Доступные команды: [info, show, add, update, remove_by_id, clear, save, execute_script, exit, add_if_min, remove_greater, remove_lower, max_by_distance, count_less_than_distance, count_greater_than_distance]");
        System.out.println("Если хотите узнать описание конкретной команды, введите info {название_команды}");
        System.out.print("Введите команду: ");

        String inputLine = scanner.nextLine();
        while (!"exit".equals(inputLine)) {
            CommandManager.executeCommand(inputLine, commandManager);
            System.out.print("Введите команду: ");
            inputLine = scanner.nextLine();
        }
    }
}

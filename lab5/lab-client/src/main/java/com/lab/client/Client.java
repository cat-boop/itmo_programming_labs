package com.lab.client;

import com.lab.client.MainClasses.CollectionManager;
import com.lab.client.MainClasses.CommandManager;
import com.lab.client.MainClasses.Console;
import com.lab.client.MainClasses.FileManager;
import com.lab.client.Utility.RouteReader;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class that start interactive mode
 * @author Sushenko Roman P3115
 */
public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try {
            String fileName = args[0];
            Scanner scanner = new Scanner(System.in);

            RouteReader routeReader = new RouteReader(scanner);
            FileManager fileManager = new FileManager(fileName);
            CollectionManager collectionManager = new CollectionManager(fileManager.readFromFile());
            CommandManager commandManager = new CommandManager(fileManager, routeReader, collectionManager);

            Console console = new Console(scanner, commandManager);
            console.startInteractiveMode();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Имя файла не указано");
        } catch (FileNotFoundException e) {
            System.out.println("Файла с таким названием не существует");
        }

    }
}

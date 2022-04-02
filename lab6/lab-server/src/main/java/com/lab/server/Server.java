package com.lab.server;

import com.lab.common.Exceptions.FileReadPermissionException;
import com.lab.common.Exceptions.RouteValidateException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public final class Server {
    private static final int PORT = 1658;

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try {
            String fileName = args[0];
            File file = new File(fileName);

            FileManager fileManager = new FileManager(file);
            CollectionManager collectionManager = new CollectionManager(fileManager.readElementsFromFile());
            CommandManager commandManager = new CommandManager(fileManager, collectionManager);

            Scanner scanner = new Scanner(System.in);
            ConsoleListener consoleListener = new ConsoleListener(commandManager, scanner);
            consoleListener.start();

            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (ConsoleListener.isServerShouldWork()) {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    Application application = new Application(commandManager, inputStream, outputStream);
                    application.run();
                }
            } catch (IOException e) {
                System.out.println("Server closed");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Имя файла не указано");
        } catch (FileNotFoundException | FileReadPermissionException | RouteValidateException e) {
            System.out.println(e.getMessage());
        }
    }
}

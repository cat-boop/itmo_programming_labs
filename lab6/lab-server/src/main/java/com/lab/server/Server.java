package com.lab.server;

import com.lab.common.Exceptions.FileReadPermissionException;
import com.lab.common.Exceptions.RouteValidateException;
import com.lab.common.util.FileManager;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public final class Server {
    private static final int PORT = 1658;
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Server.class);

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
            LOGGER.info("Файл успешно прочитан, ожидание подключения");

            Scanner scanner = new Scanner(System.in);
            ServerConsoleListener serverConsoleListener = new ServerConsoleListener(commandManager, scanner);
            serverConsoleListener.start();

            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                //TODO когда буду делать nio server надо не делать так
                while (true) {
                    Socket socket = serverSocket.accept();
                    BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
                    BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                    Application application = new Application(commandManager, inputStream, outputStream);
                    application.run();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                LOGGER.error(e.getMessage());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Имя файла не указано");
            LOGGER.error("Имя файла не указано");
        } catch (FileNotFoundException | FileReadPermissionException | RouteValidateException e) {
            System.out.println(e.getMessage());
            LOGGER.error(e.getMessage());
        }
    }
}

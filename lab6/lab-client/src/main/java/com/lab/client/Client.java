package com.lab.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public final class Client {
    private static final int TIMEOUT = 3000;
    private static final int PORT = 1658;

    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите host: ");
        String host = scanner.nextLine();

        try (Socket socket = new Socket(host, PORT);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());) {
            socket.setSoTimeout(TIMEOUT);
            Application application = new Application(socket, objectInputStream, objectOutputStream);
            application.startInteractiveMode();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.lab.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
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
        InetSocketAddress socketAddress = new InetSocketAddress(host, PORT);

        try (Selector selector = Selector.open();
             SocketChannel socketChannel = SocketChannel.open()) {

            socketChannel.configureBlocking(false);
            socketChannel.connect(socketAddress);
            socketChannel.finishConnect();
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            Application application = new Application(selector, socketChannel, scanner);
            application.startInteractiveMode();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

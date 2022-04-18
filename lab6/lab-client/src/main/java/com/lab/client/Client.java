package com.lab.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.Scanner;

public final class Client {
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
             SocketChannel socketChannel = SocketChannel.open(socketAddress)) {

            socketChannel.finishConnect();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

            Application application = new Application(selector, socketChannel, scanner);
            application.startInteractiveMode();

        } catch (IOException e) {
            System.out.println("Сервер отдыхает");
        } catch (UnresolvedAddressException e) {
            System.out.println("Сервер с данным адресом недоступен");
        }
    }
}

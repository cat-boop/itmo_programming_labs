package com.lab.client;

import com.lab.common.Exceptions.SerializeException;
import com.lab.common.util.CommandAnalyzer;
import com.lab.common.util.Deserializer;
import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.common.util.RouteReader;
import com.lab.common.util.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Application {
    private final Selector selector;
    private final SocketChannel socketChannel;
    private final Scanner scanner;

    public Application(Selector selector, SocketChannel socketChannel, Scanner scanner) {
        this.selector = selector;
        this.socketChannel = socketChannel;
        this.scanner = scanner;
    }

    public void startInteractiveMode() throws IOException {
        System.out.println("Подключился к серверу");
        System.out.print("Введите команду: ");
        String command = scanner.nextLine();

        while (!"exit".equals(command)) {

            if (sendRequest(command)) {
                Response response = receiveResponse();
                System.out.println("Ответ с сервера: " + response.getServerMessage());
            }

            System.out.print("Введите команду: ");
            command = scanner.nextLine();

        }
        System.out.print("Отключился от сервера");
    }

    private boolean sendRequest(String command) {
        try {
            RouteReader routeReader = new RouteReader(scanner);
            CommandAnalyzer commandAnalyzer = new CommandAnalyzer(command, routeReader, false);
            Request request = new Request(commandAnalyzer.getCommandName(), commandAnalyzer.getCommandArgument(), commandAnalyzer.getRoute());
            ByteBuffer byteBuffer = Serializer.serializeRequest(request);
            System.out.println("write " + byteBuffer.array().length + " " + socketChannel.write(byteBuffer));
            final long time = 1000;
            Thread.sleep(time);
            return true;
        } catch (IllegalStateException | SerializeException | IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Response receiveResponse() throws IOException {
        final int bufferSize = 1024;
        ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            SelectionKey key = iter.next();

            if (key.isReadable()) {
                System.out.println("read " + socketChannel.read(byteBuffer) + " bytes");
                byteBuffer.flip();
                byte[] serializedResponse = new byte[byteBuffer.remaining()];
                byteBuffer.get(serializedResponse);
                return Deserializer.deserializeResponse(serializedResponse);
            }
        }
    }
}

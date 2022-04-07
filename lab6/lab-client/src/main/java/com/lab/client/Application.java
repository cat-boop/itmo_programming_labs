package com.lab.client;

import com.lab.common.Exceptions.DeserializeException;
import com.lab.common.Exceptions.FileReadPermissionException;
import com.lab.common.Exceptions.ReadElementFromScriptException;
import com.lab.common.Exceptions.SerializeException;
import com.lab.common.util.Deserializer;
import com.lab.common.util.FileManager;
import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.common.util.Serializer;

import java.io.File;
import java.io.FileNotFoundException;
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
    private final Scanner consoleScanner;

    public Application(Selector selector, SocketChannel socketChannel, Scanner consoleScanner) {
        this.selector = selector;
        this.socketChannel = socketChannel;
        this.consoleScanner = consoleScanner;
    }

    public void startInteractiveMode() throws IOException {
        RouteReader consoleRouteReader = new RouteReader(consoleScanner, false);
        System.out.println("Подключился к серверу");

        System.out.print("Введите команду: ");
        String command = consoleScanner.nextLine();
        while (!"exit".equals(command)) {
            try {
                CommandAnalyzer commandAnalyzer = new CommandAnalyzer(command);
                if (commandAnalyzer.isCommandScript()) {
                    executeScript(commandAnalyzer.getCommandArgument());
                } else {
                    sendReceiveLoop(command, consoleRouteReader);
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("Введите команду: ");
            command = consoleScanner.nextLine();
        }
        System.out.print("Отключился от сервера");
    }

    private void executeScript(String scriptName) throws IOException {
        try {
            File file = new File(scriptName);
            FileManager fileManager = new FileManager(file);
            Scanner scannerToScript = fileManager.getScannerToFile();
            RouteReader scriptRouteReader = new RouteReader(scannerToScript, true);

            System.out.println("Исполнение скрипта \"" + scriptName + "\" начато");
            while (scannerToScript.hasNextLine()) {
                String scriptCommand = scannerToScript.nextLine();
                sendReceiveLoop(scriptCommand, scriptRouteReader);
            }
            System.out.println("Исполнение скрипта \"" + scriptName + "\" завершено");

        } catch (FileNotFoundException | FileReadPermissionException | ReadElementFromScriptException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendReceiveLoop(String command, RouteReader routeReader) throws IOException {
        try {
            Request request = RequestCreator.createRequest(command, routeReader);
            byte[] serializedRequest = Serializer.serializeRequest(request);
            socketChannel.write(ByteBuffer.wrap(serializedRequest));
            Response response = receiveResponse();
            System.out.println("Ответ с сервера: " + response.getServerMessage());
        } catch (SerializeException | DeserializeException e) {
            System.out.println(e.getMessage());
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
                int bytesRead = socketChannel.read(byteBuffer);
                if (bytesRead <= 0) {
                    continue;
                }
                byteBuffer.flip();
                byte[] serializedResponse = new byte[byteBuffer.remaining()];
                byteBuffer.get(serializedResponse);
                return Deserializer.deserializeResponse(serializedResponse);
            }
        }
    }
}

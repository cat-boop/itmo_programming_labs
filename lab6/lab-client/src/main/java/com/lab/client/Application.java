package com.lab.client;

import com.lab.common.util.CommandAnalyzer;
import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.common.util.RouteReader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Application {
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public Application(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void startInteractiveMode() throws IOException {
        Scanner scanner = new Scanner(System.in);
        RouteReader routeReader = new RouteReader(scanner);

        System.out.println("Подключился к серверу с номером порта " + socket.getPort());
        System.out.print("Введите команду: ");
        String command = scanner.nextLine();
        while (!"exit".equals(command)) {
            try {
                CommandAnalyzer commandAnalyzer = new CommandAnalyzer(command, routeReader, false);
                Request request = new Request(commandAnalyzer.getCommandName(), commandAnalyzer.getCommandArgument(), commandAnalyzer.getRoute());
                //ByteBuffer byteBuffer = Serializer.serializeRequest(request);

                //SocketChannel socketChannel = socket.getChannel();
                //socketChannel.write(byteBuffer);
                //dataOutputStream.write(byteBuffer.array());
                //dataOutputStream.flush();
                outputStream.writeObject(request);
                outputStream.flush();

                Response response = (Response) inputStream.readObject();
                //final int arraySize = 10000;
                //byte[] serializedResponse = new byte[arraySize];
                //int size = dataInputStream.read(serializedResponse);
                //Response response = Deserializer.deserializeResponse(ByteBuffer.wrap(serializedResponse, 0, size).array());
                System.out.println("Ответ с сервера: " + response.getServerMessage());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Ошибка при получении ответа с сервера");
            }

            System.out.print("Введите команду: ");
            command = scanner.nextLine();
        }
        System.out.print("Отключился от сервера");
    }
}

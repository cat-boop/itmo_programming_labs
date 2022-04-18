package com.lab.server;

import com.lab.common.Exceptions.DeserializeException;
import com.lab.common.Exceptions.SerializeException;
import com.lab.common.util.Deserializer;
import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.common.util.Serializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private final CommandManager commandManager;
    private final BufferedInputStream inputStream;
    private final BufferedOutputStream outputStream;

    public Application(CommandManager commandManager, BufferedInputStream inputStream, BufferedOutputStream outputStream) {
        this.commandManager = commandManager;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void run() {
        try {
            System.out.println("Клиент подключился");
            ServerLogger.logInfoMessage("Клиент подключился");

            Request request = receiveRequest();
            while (true) {
                System.out.println("Клиент отправил запрос: " + request);
                ServerLogger.logInfoMessage("Клиент отправил запрос: {}", request);

                Response serverResponse = commandManager.executeCommand(request);
                ServerLogger.logInfoMessage("Результат исполнения запроса: {}", serverResponse);

                byte[] serializedResponse = Serializer.serializeResponse(serverResponse);
                outputStream.write(serializedResponse);
                outputStream.flush();
                ServerLogger.logInfoMessage("Отправлен ответ размером {} байт", serializedResponse.length);

                request = receiveRequest();
            }
        } catch (IOException | SerializeException | DeserializeException e) {
            //e.printStackTrace();
            System.out.println("Клиент отключился");
//            ServerLogger.logDebugMessage(e.getMessage());
            ServerLogger.logInfoMessage("Клиент отключился");
        }
    }

    private Request receiveRequest() throws IOException {
        final int size = 1024;
        byte[] serializedRequest = new byte[size];
        int bytesRead = inputStream.read(serializedRequest);
        if (bytesRead == -1) {
            throw new IOException();
        }
        ServerLogger.logDebugMessage("Прочитано {} байт", bytesRead);
        try {
            return Deserializer.deserializeRequest(serializedRequest);
        } catch (DeserializeException e) {
            int last = inputStream.available();
            int len = bytesRead;
            List<ByteBuffer> list = new ArrayList<>();
            while (last > 0) {
                ServerLogger.logDebugMessage("Осталось прочитать {} байт", last);
                byte[] arr = new byte[last];
                int bytes = inputStream.read(arr);
                len += bytes;
//                if (Integer.MAX_VALUE - len < 70000) {
//                    System.out.println("чел ты");
//                    throw new IOException();
//                }
                if (bytes == -1) {
                    throw new IOException();
                }
                list.add(ByteBuffer.wrap(arr));
                ServerLogger.logDebugMessage("Прочитано {} байт", bytes);
                last = inputStream.available();
            }
            ByteBuffer byteBuffer = ByteBuffer.allocate(len);
            byteBuffer.put(serializedRequest);
            list.forEach(byteBuffer::put);
            return Deserializer.deserializeRequest(byteBuffer.array());
        }
    }
}

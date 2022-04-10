package com.lab.server;

import com.lab.common.Exceptions.DeserializeException;
import com.lab.common.Exceptions.SerializeException;
import com.lab.common.util.Deserializer;
import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.common.util.Serializer;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Application {
    private final CommandManager commandManager;
    private final BufferedInputStream inputStream;
    private final BufferedOutputStream outputStream;
    private final Logger logger;

    public Application(CommandManager commandManager, BufferedInputStream inputStream, BufferedOutputStream outputStream) {
        this.commandManager = commandManager;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        logger = (Logger) LoggerFactory.getLogger(Application.class);
    }

    public void run() {
        try {
            System.out.println("Клиент подключился");
            logger.info("Клиент подключился");

            Request request = receiveRequest();
            while (true) {
                System.out.println("Клиент отправил запрос: " + request);
                logger.info("Клиент отправил запрос: {}", request);

                String serverResponseMessage = commandManager.executeCommand(request);
                logger.info("Результат исполнения запроса: {}", serverResponseMessage);

                Response response = new Response(serverResponseMessage);
                byte[] serializedResponse = Serializer.serializeResponse(response);
                outputStream.write(serializedResponse);
                outputStream.flush();
                logger.info("Отправлен ответ размером {} байт", serializedResponse.length);

                request = receiveRequest();
            }
        } catch (IOException | SerializeException | DeserializeException e) {
            //e.printStackTrace();
            System.out.println("Клиент отключился");
            logger.info("Клиент отключился");
        }
    }

    private Request receiveRequest() throws IOException {
        while (inputStream.available() == 0) {
            continue;
        }
        final int size = inputStream.available() + 4096;
        byte[] serializedRequest = new byte[size];
        int bytesRead = inputStream.read(serializedRequest);
        if (bytesRead == -1) {
            throw new IOException();
        }
        logger.debug("Прочитано {} байт", bytesRead);
        return Deserializer.deserializeRequest(serializedRequest);
    }
}

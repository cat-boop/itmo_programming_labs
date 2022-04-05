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
import java.io.InputStream;
import java.io.OutputStream;

public class Application {
    private final CommandManager commandManager;
    private final BufferedInputStream inputStream;
    private final BufferedOutputStream outputStream;

    public Application(CommandManager commandManager, InputStream inputStream, OutputStream outputStream) {
        this.commandManager = commandManager;
        this.inputStream = new BufferedInputStream(inputStream);
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    public void run() {
        try {
            System.out.println("Client has connected");

            final int size = 1000;
            byte[] serializedRequest = new byte[size];
            System.out.println(inputStream.read(serializedRequest));
            Request request = Deserializer.deserializeRequest(serializedRequest);
            while (ConsoleListener.isServerShouldWork()) {
                System.out.println("Client request = " + request);
                String serverResponseMessage = commandManager.executeCommand(request);

                Response response = new Response(serverResponseMessage);
                outputStream.write(Serializer.serializeResponse(response));
                outputStream.flush();
                System.out.println("sent response");
                final long time = 1000;
                Thread.sleep(time);

                serializedRequest = new byte[size];
                System.out.println(inputStream.read(serializedRequest));
                request = Deserializer.deserializeRequest(serializedRequest);
            }
        } catch (IOException | SerializeException | DeserializeException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Client has disconnected");
        }
    }
}

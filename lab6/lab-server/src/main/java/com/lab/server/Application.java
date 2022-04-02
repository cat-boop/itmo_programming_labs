package com.lab.server;

import com.lab.common.util.Request;
import com.lab.common.util.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Application {
    private final CommandManager commandManager;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    public Application(CommandManager commandManager, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        this.commandManager = commandManager;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void run() {
        try {
            System.out.println("Client has connected");

            Request request = (Request) inputStream.readObject();
            while (ConsoleListener.isServerShouldWork()) {
                System.out.println("Client request = " + request);
                String serverResponseMessage = commandManager.executeCommand(request);

                Response response = new Response(serverResponseMessage);
                outputStream.writeObject(response);
                outputStream.flush();

                request = (Request) inputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println(e.getMessage());
            System.out.println("Client has disconnected");
        }
    }
}

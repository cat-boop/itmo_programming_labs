package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CommandManager;
import com.lab.server.ServerLogger;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("Завершает работу серверного приложения", true);
    }

    @Override
    public Response execute(Request request) {
        Response response = CommandManager.getCommands().get("save").execute(request);

        System.out.println(response);
        System.out.println("Работа сервера остановлена");

        ServerLogger.logInfoMessage(response.toString());
        ServerLogger.logInfoMessage("Работа сервера остановлена");

        System.exit(0);
        return null;
    }
}

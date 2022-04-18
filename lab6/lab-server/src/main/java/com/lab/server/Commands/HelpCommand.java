package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CommandManager;
import java.util.stream.Collectors;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("вывести справку по доступным командам", false);
    }

    @Override
    public Response execute(Request request) {
        return new Response(CommandManager.getCommands().entrySet().stream().filter(entry -> !entry.getValue().isServerCommand()).map(entry -> entry.getKey() + ": " + entry.getValue().getCommandDescription()).collect(Collectors.joining("\n")));
    }
}

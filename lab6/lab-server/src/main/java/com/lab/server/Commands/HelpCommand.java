package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CommandManager;

import java.util.Map;

public class HelpCommand implements Command {
    @Override
    public String execute(Request request) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Map.Entry<String, Command> commandEntry : CommandManager.getCommands().entrySet()) {
            stringBuilder.append(commandEntry.getKey()).append(":\n");
        }
        return stringBuilder.toString();
    }
}

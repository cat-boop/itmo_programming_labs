package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.ConsoleListener;

public class ExitCommand implements Command {
    @Override
    public String execute(Request request) {
        ConsoleListener.setServerShouldWork(false);
        return null;
    }
}

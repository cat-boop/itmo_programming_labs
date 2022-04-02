package com.lab.server.Commands;

import com.lab.common.util.Request;

public interface Command {
    String execute(Request request);
}

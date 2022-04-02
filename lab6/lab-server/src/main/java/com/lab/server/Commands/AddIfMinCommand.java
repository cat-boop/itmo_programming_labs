package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

public class AddIfMinCommand implements Command {
    private final CollectionManager collectionManager;

    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        if (collectionManager.addIfMin(request.getRouteToSend())) {
            return "Элемент успешно добавлен";
        } else {
            return "Элемент не добавлен, так как он не меньше минимального";
        }
    }
}

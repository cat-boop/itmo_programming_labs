package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

public class AddIfMinCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public AddIfMinCommand(CollectionManager collectionManager) {
        super("добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (collectionManager.addIfMin(request.getRouteToSend())) {
            return new Response("Элемент успешно добавлен");
        } else {
            return new Response("Элемент не добавлен, так как он не меньше минимального");
        }
    }
}

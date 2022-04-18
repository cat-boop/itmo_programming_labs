package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("добавить новый элемент в коллекцию", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        boolean success = collectionManager.add(request.getRouteToSend());
        if (success) {
            return new Response("Элемент успешно добавлен");
        } else {
            return new Response("Ошибка при добавлении элемента, возможно, такой элемент уже существует");
        }
    }
}

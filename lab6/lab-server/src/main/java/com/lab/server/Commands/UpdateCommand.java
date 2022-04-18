package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

import java.util.NoSuchElementException;

public class UpdateCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super("обновить значение элемента коллекции, id которого равен заданному", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        long id = request.getCommandArgument().longValue();
        try {
            collectionManager.updateById(id, request.getRouteToSend());
            return new Response("Элемент с id = " + id + " успешно обновлен");
        } catch (NoSuchElementException e) {
            return new Response("Элемента с id = " + id + " не существует");
        }
    }
}

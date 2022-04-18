package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

import java.util.NoSuchElementException;

public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("удалить элемент из коллекции по его id", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        long id = request.getCommandArgument().longValue();
        try {
            collectionManager.removeById(id);
            return new Response("Элемент с id = " + id + " удален из коллекции");
        } catch (NoSuchElementException e) {
            return new Response("Элемента с id = " + id + " не существует");
        }
    }
}

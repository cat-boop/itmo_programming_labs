package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

import java.util.NoSuchElementException;

public class RemoveByIdCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        long id = Long.parseLong(request.getCommandArgument());
        try {
            collectionManager.removeById(id);
            return "Элемент с id = " + id + " удален из коллекции";
        } catch (NoSuchElementException e) {
            return "Элемента с id = " + id + " не существует";
        }
    }
}

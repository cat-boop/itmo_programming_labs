package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

import java.util.NoSuchElementException;

public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        long id = request.getCommandArgument().longValue();
        try {
            collectionManager.updateById(id, request.getRouteToSend());
            return "Элемент с id = " + id + " успешно обновлен";
        } catch (NoSuchElementException e) {
            return "Элемента с id = " + id + " не существует";
        }
    }
}

package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        boolean success = collectionManager.add(request.getRouteToSend());
        if (success) {
            return "Элемент успешно добавлен";
        } else {
            return "Ошибка при добавлении элемента, возможно, такой элемент уже существует";
        }
    }
}

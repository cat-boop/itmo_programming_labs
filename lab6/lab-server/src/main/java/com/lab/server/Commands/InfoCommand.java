package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        return "Тип коллекции - " + collectionManager.getCollectionName() + "\n"
                + "Количество элементов - " + collectionManager.getSize() + "\n"
                + "Дата инициализации - " + collectionManager.getCreationDate();
    }
}

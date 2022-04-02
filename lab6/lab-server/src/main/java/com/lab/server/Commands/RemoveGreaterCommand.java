package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

public class RemoveGreaterCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        return "Было удалено " + collectionManager.removeGreater(request.getRouteToSend()) + " элементов";
    }
}

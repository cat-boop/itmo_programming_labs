package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

public class RemoveGreaterCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super("удалить из коллекции все элементы, превышающие заданный", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        return new Response("Было удалено " + collectionManager.removeGreater(request.getRouteToSend()) + " элементов");
    }
}

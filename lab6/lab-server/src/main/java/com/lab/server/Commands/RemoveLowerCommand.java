package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

public class RemoveLowerCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("удалить из коллекции все элементы, меньшие, чем заданный", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        return new Response("Было удалено " + collectionManager.removeLower(request.getRouteToSend()) + " элементов");
    }
}

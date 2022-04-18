package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

public class InfoCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        return new Response("Тип коллекции - " + collectionManager.getCollectionName() + "\n"
                + "Количество элементов - " + collectionManager.getSize() + "\n"
                + "Дата инициализации - " + collectionManager.getCreationDate());
    }
}

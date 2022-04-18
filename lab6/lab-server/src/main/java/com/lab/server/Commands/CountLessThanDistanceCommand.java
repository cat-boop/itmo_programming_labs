package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

public class CountLessThanDistanceCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public CountLessThanDistanceCommand(CollectionManager collectionManager) {
        super("вывести количество элементов, значение поля distance которых меньше заданного", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        double distance = request.getCommandArgument().doubleValue();
        return new Response("Количество маршрутов с протяженностью меньше чем " + distance
                + " равно " + collectionManager.countLessThanDistance(distance));
    }
}

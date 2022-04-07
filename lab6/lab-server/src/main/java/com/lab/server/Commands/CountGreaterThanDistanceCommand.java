package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

public class CountGreaterThanDistanceCommand implements Command {
    private final CollectionManager collectionManager;

    public CountGreaterThanDistanceCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        double distance = request.getCommandArgument().doubleValue();
        return "Количество маршрутов с протяженностью больше чем " + distance
                + " равно " + collectionManager.countGreaterThanDistance(distance);

    }
}

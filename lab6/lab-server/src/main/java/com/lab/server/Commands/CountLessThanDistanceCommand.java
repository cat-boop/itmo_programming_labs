package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

public class CountLessThanDistanceCommand implements Command {
    private final CollectionManager collectionManager;

    public CountLessThanDistanceCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        double distance = Double.parseDouble(request.getCommandArgument());
        return "Количество маршрутов с протяженностью меньше чем " + distance
                + " равно " + collectionManager.countLessThanDistance(distance);
    }
}

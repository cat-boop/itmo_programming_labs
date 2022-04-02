package com.lab.server.Commands;

import com.lab.common.Data.Route;
import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

import java.util.NoSuchElementException;

public class MaxByDistanceCommand implements Command {
    private final CollectionManager collectionManager;

    public MaxByDistanceCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        try {
            Route route = collectionManager.maxByDistance();
            return route.toString();
        } catch (NoSuchElementException e) {
            return "Коллекция пуста";
        }
    }
}

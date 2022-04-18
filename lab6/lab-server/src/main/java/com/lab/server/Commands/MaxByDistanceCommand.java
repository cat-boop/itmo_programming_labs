package com.lab.server.Commands;

import com.lab.common.Data.Route;
import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;

import java.util.NoSuchElementException;

public class MaxByDistanceCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public MaxByDistanceCommand(CollectionManager collectionManager) {
        super("вывести любой объект из коллекции, значение поля distance которого является максимальным", false);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            Route route = collectionManager.maxByDistance();
            return new Response(route.toString());
        } catch (NoSuchElementException e) {
            return new Response("Коллекция пуста");
        }
    }
}

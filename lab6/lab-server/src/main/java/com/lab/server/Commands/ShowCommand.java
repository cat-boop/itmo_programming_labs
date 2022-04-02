package com.lab.server.Commands;

import com.lab.common.Data.Route;
import com.lab.common.util.Request;
import com.lab.server.CollectionManager;

import java.util.stream.Collectors;

public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(Request request) {
        return collectionManager.getCollection().stream().map(Route::toString).collect(Collectors.joining("\n"));
    }
}

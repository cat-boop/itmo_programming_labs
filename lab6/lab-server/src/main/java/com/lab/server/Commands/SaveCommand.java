package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.server.CollectionManager;
import com.lab.common.util.FileManager;

public class SaveCommand implements Command {
    private final FileManager fileManager;
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(Request request) {
        return fileManager.saveToFile(collectionManager.getCollection());
    }
}

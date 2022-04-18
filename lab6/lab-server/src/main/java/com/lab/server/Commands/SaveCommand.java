package com.lab.server.Commands;

import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.CollectionManager;
import com.lab.common.util.FileManager;

public class SaveCommand extends AbstractCommand {
    private final FileManager fileManager;
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        super("сохранить коллекцию в файл", true);
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        return new Response(fileManager.saveToFile(collectionManager.getCollection()));
    }
}

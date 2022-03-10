package com.lab.client.MainClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSyntaxException;
import com.lab.client.Data.Route;
import com.lab.client.Exceptions.FileReadPermissionException;
import com.lab.client.Exceptions.RouteValidateException;
import com.lab.client.Utility.RouteValidator;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.Scanner;

/**
 * Class that operates all files
 */
public class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * reads and return route from json file using JsonParser utility class
     *
     * @return list of routes from file
     */
    public List<Route> readFromFile() throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Файла с таким названием не существует");
        }
        if (!file.canRead()) {
            throw new FileReadPermissionException("Нет прав для чтения файла");
        }
        Scanner scanner = new Scanner(file);
        StringBuilder inputArray = new StringBuilder();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            inputArray.append(nextLine);
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonPrimitive) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();
        Route[] routes;
        try {
            routes = gson.fromJson(inputArray.toString(), Route[].class);
        } catch (JsonSyntaxException e) {
            throw new RouteValidateException("В исходном JSON-файле содержатся ошибки");
        }
        if (!RouteValidator.validateRoutes(routes)) {
            throw new RouteValidateException("В исходном JSON-файле содержатся ошибки");
        }
        return new ArrayList<>(Arrays.asList(routes));
    }

    /**
     * save collection to json file
     *
     * @param set collection to save
     */
    public void saveToFile(Set<Route> set) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))).setPrettyPrinting().create();
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
            String toPrint = gson.toJson(set);
            out.write(toPrint.getBytes());
            System.out.println("Коллекция успешно сохранена в файл");
        } catch (IOException e) {
            System.out.println("Нет прав записи в файл. Коллекция не сохранена.");
        }
    }
}

package com.lab.client.MainClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializer;
import com.lab.client.Data.Route;
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
        StringBuilder inputArray = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            inputArray.append(nextLine);
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonPrimitive) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();
        Route[] routes = gson.fromJson(inputArray.toString(), Route[].class);
        RouteValidator.validateRoutes(routes);
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

    /**
     * @return scanner for script if script exist, else return null
     */
    public static Scanner getScannerToScript(String scriptName) {
        File file = new File(scriptName);
        if (file.exists() && !file.canRead()) {
            System.out.println("Нет прав для чтения скрипта");
            return null;
        }
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Скрипта с таким именем не существует, проверьте правильность названия.");
            return null;
        }
    }
}

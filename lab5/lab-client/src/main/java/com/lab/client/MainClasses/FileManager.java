package com.lab.client.MainClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.lab.client.Data.Route;
import com.lab.client.Utility.JsonParser;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;

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
     * @return list of routes from file
     */
    public ArrayList<Route> readFromFile() {
        File file = new File(fileName);
        if (file.exists() && !file.canRead()) {
            System.out.println("Нет прав для чтения файла");
            return new ArrayList<>();
        } else {
            return JsonParser.parseJson(file);
        }
    }

    /**
     * save collection to json file
     * @param treeSet collection to save
     */
    public void saveToFile(TreeSet<Route> treeSet) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))).setPrettyPrinting().create();
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
            String toPrint = gson.toJson(treeSet);
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

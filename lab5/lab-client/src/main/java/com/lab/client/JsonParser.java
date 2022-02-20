package com.lab.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSyntaxException;
import com.lab.client.Data.Route;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public final class JsonParser {

    private JsonParser() {
        throw new UnsupportedOperationException("This class should not be instantiated");
    }

    public static ArrayList<Route> parseJson(File file) {
        StringBuilder inputArray = new StringBuilder();
        ArrayList<Route> arrayList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                inputArray.append(nextLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла с таким именем не существует");
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonPrimitive) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();
        try {
            Route[] routes = gson.fromJson(inputArray.toString(), Route[].class);
            if (!RouteValidator.validateRoutes(routes)) {
                System.exit(0);
            }
            arrayList.addAll(Arrays.asList(routes));
        } catch (JsonSyntaxException | DateTimeParseException e) {
            System.out.println("В исходном файле содержатся ошибки.");
            System.exit(0);
        }
        return arrayList;
    }
}

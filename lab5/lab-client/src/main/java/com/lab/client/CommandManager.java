package com.lab.client;

import com.lab.client.Data.Route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CommandManager {
    private final Map<String, String> map;
    private final CollectionManager collectionManager;
    private final RouteReader routeReader;
    private final FileManager fileManager;
    private boolean isScriptExecuting;

    public CommandManager(FileManager fileManager, RouteReader routeReader) {
        this.fileManager = fileManager;
        this.routeReader = routeReader;
        this.collectionManager = new CollectionManager(fileManager.readFromFile());
        this.isScriptExecuting = false;
        map = new HashMap<>();
        map.put("help", "Доступные команды: [info, show, add, update, remove_by_id, clear, save, execute_script, exit, add_if_min, remove_greater, remove_lower, max_by_distance, count_less_than_distance, count_greater_than_distance]\n"
                + "Если хотите узнать описание конкретной команды, введите info {название_команды}");
        map.put("info", "Выводит информацию о коллекции");
        map.put("show", "Выводит все элементы коллекции");
        map.put("add", "Добавляет элемент в коллекцию");
        map.put("update", "Обновляет значение элемента коллекции, id которого равен заданному");
        map.put("remove_by_id", "Удаляет элемент из коллекции по его id");
        map.put("clear", "Очищает коллекцию");
        map.put("save", "Сохраняет коллекцию в файл");
        map.put("execute_script", "Считывает и исполняет скрипт из указанного файла.\nВ скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        map.put("exit", "Завершает программу (без сохранения в файл");
        map.put("add_if_min", "Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        map.put("remove_greater", "Удаляет из коллекции все элементы, превышающие заданный");
        map.put("remove_lower", "Удаляет из коллекции все элементы, меньшие, чем заданный");
        map.put("max_by_distance", "Выводит объект коллекции, значение поля distance которого является максимальным");
        map.put("count_less_than_distance", "Выводит количество элементов, значение поля distance которых меньше заданного");
        map.put("count_greater_than_distance", "Выводит количество элементов, значение поля distance которых больше заданного");
    }

    public void help() {
        System.out.println(map.get("help"));
    }

    public void info(String command) {
        System.out.println(map.getOrDefault(command, "Такой команды не существует."));
    }

    public void info() {
        System.out.println("Тип коллекции - " + collectionManager.getCollectionName());
        System.out.println("Количество элементов - " + collectionManager.getSize());
        System.out.println("Дата инициализации - " + collectionManager.getCreationDate());
    }

    public void show() {
        for (Route route : collectionManager.getTreeSet()) {
            System.out.println(route);
        }
    }

    public void add() {
        Route route = getRoute();
        if (route == null) {
            return;
        }
        boolean success = collectionManager.add(route);
        if (!success) {
            System.out.println("Ошибка при добавлении элемента. Возможно, такой элемент уже существует.");
        }
    }

    public void update(String argument) {
        try {
            long id = Long.parseLong(argument);
            if (collectionManager.existElementWithId(id)) {
                Route route = getRoute();
                if (route == null) {
                    return;
                }
                collectionManager.updateById(id, route);
                System.out.println("Элемент успешно обновлён.");
            } else {
                System.out.println("Элемента с таким id не существует.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при вводе целого числа.");
        }
    }

    public void removeById(String argument) {
        try {
            long id = Long.parseLong(argument);
            if (collectionManager.existElementWithId(id)) {
                collectionManager.removeById(id);
                System.out.println("Элемент успешно удалён");
            } else {
                System.out.println("Элемента с таким id не существует.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при вводе целого числа.");
        }
    }

    public void clear() {
        collectionManager.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    public void save() {
        fileManager.saveToFile(collectionManager.getTreeSet());
    }

    public void executeScript(String scriptName) {
        Scanner scannerToScript = FileManager.getScannerToScript(scriptName);
        if (scannerToScript == null) {
            return;
        }
        Scanner consoleScanner = routeReader.getScanner();
        routeReader.setScanner(scannerToScript);
        this.isScriptExecuting = true;
        while (scannerToScript.hasNext()) {
            String inputCommand = scannerToScript.nextLine();
            System.out.println("Исполнение команды \"" + inputCommand + "\"");
            executeCommand(inputCommand, this);
        }
        System.out.println("Исполнение скрипта завершено");
        routeReader.setScanner(consoleScanner);
        this.isScriptExecuting = false;
    }

    public void addIfMin() {
        Route route = getRoute();
        if (route == null) {
            return;
        }
        boolean success = collectionManager.addIfMin(route);
        if (success) {
            System.out.println("Элемент успешно добавлен.");
        } else {
            System.out.println("Элемент не меньше наименьшего элемента коллекции.");
        }
    }

    public void removeGreater() {
        collectionManager.removeGreater(routeReader.readRouteFromConsole());
    }

    public void removeLower() {
        collectionManager.removeLower(routeReader.readRouteFromConsole());
    }

    public void maxByDistance() {
        try {
            System.out.println(collectionManager.maxByDistance());
        } catch (NoSuchElementException e) {
            System.out.println("Коллекция пуста.");
        }
    }

    public void countLessThanDistance(String argument) {
        try {
            double distance = Double.parseDouble(argument);
            System.out.println("Количество маршрутов с протяженностью меньше чем " + distance
                    + " равно " + collectionManager.countLessThanDistance(distance));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при вводе числа с плавающей точкой");
        }
    }

    public void countGreaterThanDistance(String argument) {
        try {
            double distance = Double.parseDouble(argument);
            System.out.println("Количество маршрутов с протяженностью больше чем " + distance
                    + " равно " + collectionManager.countGreaterThanDistance(distance));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при вводе числа с плавающей точкой");
        }
    }

    public Route getRoute() {
        if (isScriptExecuting) {
            System.out.println("Попытка чтения элемента из скрипта");
            Route route = routeReader.readRouteFromScript();
            if (route != null) {
                System.out.println("Элемент успешно считан");
            }
            return route;
        } else {
            return routeReader.readRouteFromConsole();
        }
    }

    public static void executeCommand(String inputCommand, CommandManager commandManager) {
        String[] inputLineDivided = inputCommand.trim().split(" ", 2);
        String command = inputCommandToJavaStyle(inputLineDivided[0].toLowerCase());
        Class<CommandManager> commandManagerClass = CommandManager.class;
        Method methodToInvoke;
        try {
            if (inputLineDivided.length == 1) {
                methodToInvoke = commandManagerClass.getMethod(command);
                methodToInvoke.invoke(commandManager);
            } else {
                methodToInvoke = commandManagerClass.getMethod(command, String.class);
                methodToInvoke.invoke(commandManager, inputLineDivided[1]);
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Такой команды не существует");
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Что-то очень плохое (CommandManager class executeScript method)");
        }
    }

    private static String inputCommandToJavaStyle(String str) {
        StringBuilder result = new StringBuilder();
        boolean needUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '_') {
                if (needUpperCase) {
                    c = Character.toUpperCase(c);
                    needUpperCase = false;
                }
                result.append(c);
            } else {
                needUpperCase = true;
            }
        }
        return result.toString();
    }
}

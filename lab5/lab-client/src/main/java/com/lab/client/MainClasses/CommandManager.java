package com.lab.client.MainClasses;

import com.lab.client.Data.Route;
import com.lab.client.Utility.RouteReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Class that execute user commands
 */
public class CommandManager {
    private final Set<String> scriptNames;
    private final CollectionManager collectionManager;
    private final RouteReader routeReader;
    private final FileManager fileManager;
    private final Method[] methods;
    private boolean isScriptExecuting;

    public CommandManager(FileManager fileManager, RouteReader routeReader, CollectionManager collectionManager) {
        this.fileManager = fileManager;
        this.routeReader = routeReader;
        this.collectionManager = collectionManager;
        this.methods = CommandManager.class.getMethods();
        this.isScriptExecuting = false;
        scriptNames = new HashSet<>();
    }

    /**
     * prints supporting information
     */
    public void help() {
        System.out.println("info: Выводит информацию о коллекции");
        System.out.println("show: Выводит все элементы коллекции");
        System.out.println("add: Добавляет элемент в коллекцию");
        System.out.println("update: Обновляет значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id: Удаляет элемент из коллекции по его id");
        System.out.println("clear: Очищает коллекцию");
        System.out.println("save: Сохраняет коллекцию в файл");
        System.out.println("execute_script: Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь");
        System.out.println("add_if_min: Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("remove_greater: Удаляет из коллекции все элементы, превышающие заданный");
        System.out.println("remove_lower: Удаляет из коллекции все элементы, меньшие, чем заданный");
        System.out.println("max_by_distance: Выводит объект коллекции, значение поля distance которого является максимальным");
        System.out.println("count_less_than_distance: Выводит количество элементов, значение поля distance которых меньше заданного");
        System.out.println("count_greater_than_distance: Выводит количество элементов, значение поля distance которых больше заданного");
        System.out.println("exit: Завершает выполнение программы без сохранения в файл");
    }

    /**
     * prints info about collection
     */
    public void info() {
        System.out.println("Тип коллекции - " + collectionManager.getCollectionName());
        System.out.println("Количество элементов - " + collectionManager.getSize());
        System.out.println("Дата инициализации - " + collectionManager.getCreationDate());
    }

    /**
     * prints all elements of collection
     */
    public void show() {
        for (Route route : collectionManager.getCollection()) {
            System.out.println(route);
        }
    }

    /**
     * add new route to collection
     */
    public void add() {
        boolean success = collectionManager.add(getRoute());
        if (!success) {
            System.out.println("Ошибка при добавлении элемента. Возможно, такой элемент уже существует.");
        }
    }

    /**
     * update a route in collection
     *
     * @param argument user-entered argument, which should be a long number
     */
    public void update(String argument) {
        try {
            long id = Long.parseLong(argument);
            if (collectionManager.existElementWithId(id)) {
                collectionManager.updateById(id, getRoute());
                System.out.println("Элемент успешно обновлён.");
            } else {
                System.out.println("Элемента с таким id не существует.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при вводе целого числа.");
        }
    }

    /**
     * remove route from collection
     *
     * @param argument user-entered argument, which should be a long number
     */
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

    /**
     * remove all element from collection
     */
    public void clear() {
        collectionManager.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    /**
     * save collection to file
     */
    public void save() {
        fileManager.saveToFile(collectionManager.getCollection());
    }

    /**
     * script contains commands in the same format as the user enters them, method execute commands from script
     */
    public void executeScript(String scriptName) {
        if (scriptNames.contains(scriptName)) {
            System.out.println("Скрипты нельзя вызывать рекурсивно");
            return;
        }
        scriptNames.add(scriptName);
        Scanner scannerToScript = FileManager.getScannerToScript(scriptName);
        if (scannerToScript == null) {
            return;
        }
        System.out.println("Исполнение скрипта \"" + scriptName + "\"");
        Scanner consoleScanner = routeReader.getScanner();
        routeReader.setScanner(scannerToScript);
        this.isScriptExecuting = true;
        while (scannerToScript.hasNext()) {
            String inputCommand = scannerToScript.nextLine();
            System.out.println("Исполнение команды \"" + inputCommand + "\"");
            this.executeCommand(inputCommand);
        }
        System.out.println("Исполнение скрипта \"" + scriptName + "\" завершено");
        routeReader.setScanner(consoleScanner);
        scriptNames.remove(scriptName);
        this.isScriptExecuting = false;
    }

    /**
     * add route entered by user to collection if it's lower than the minimal route in collection
     */
    public void addIfMin() {
        boolean success = collectionManager.addIfMin(getRoute());
        if (success) {
            System.out.println("Элемент успешно добавлен.");
        } else {
            System.out.println("Элемент не меньше наименьшего элемента коллекции.");
        }
    }

    /**
     * remove all routes in collection that greater than route entered by user
     */
    public void removeGreater() {
        collectionManager.removeGreater(getRoute());
    }

    /**
     * remove all routes in collection that lower than route entered by user
     */
    public void removeLower() {
        collectionManager.removeLower(getRoute());
    }

    /**
     * print route from collection which distance is maximum
     */
    public void maxByDistance() {
        try {
            System.out.println(collectionManager.maxByDistance());
        } catch (NoSuchElementException e) {
            System.out.println("Коллекция пуста.");
        }
    }

    /**
     * prints number of routes from collection which distance less than the user-entered distance
     *
     * @param argument user-entered argument, which should be a double number
     */
    public void countLessThanDistance(String argument) {
        try {
            double distance = Double.parseDouble(argument);
            System.out.println("Количество маршрутов с протяженностью меньше чем " + distance
                    + " равно " + collectionManager.countLessThanDistance(distance));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при вводе числа с плавающей точкой");
        }
    }

    /**
     * prints number of routes from collection which distance greater than the user-entered distance
     *
     * @param argument user-entered argument, which should be a double number
     */
    public void countGreaterThanDistance(String argument) {
        try {
            double distance = Double.parseDouble(argument);
            System.out.println("Количество маршрутов с протяженностью больше чем " + distance
                    + " равно " + collectionManager.countGreaterThanDistance(distance));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при вводе числа с плавающей точкой");
        }
    }

    /**
     * method which get new route from console or from script
     *
     * @return new route read from script of from console
     * @throws com.lab.client.Exceptions.ReadElementException if script contains error
     */
    public Route getRoute() {
        if (isScriptExecuting) {
            System.out.println("Попытка чтения элемента из скрипта");
            return routeReader.readRouteFromScript();
        } else {
            return routeReader.readRouteFromConsole();
        }
    }

    /**
     * main method, that execute commands using Reflection API
     * @param inputCommand command entered by user
     */
    public boolean executeCommand(String inputCommand) {
        String[] inputLineDivided = inputCommand.trim().split(" ", 2);
        String command = inputCommandToJavaStyle(inputLineDivided[0].toLowerCase());
        if ("exit".equals(command)) {
            return true;
        }
        try {
            Method methodToInvoke = null;
            for (Method method : methods) {
                if (method.getName().equals(command)) {
                    methodToInvoke = method;
                    break;
                }
            }
            if (methodToInvoke == null) {
                throw new NoSuchMethodException();
            }
            if (inputLineDivided.length == 1) {
                methodToInvoke.invoke(this);
            } else {
                methodToInvoke.invoke(this, inputLineDivided[1]);
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Такой команды не существует");
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Что-то очень плохое (CommandManager class executeScript method)");
        }
        return false;
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

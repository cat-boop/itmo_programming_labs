package com.lab.client;

import com.lab.client.Data.Coordinates;
import com.lab.client.Data.Location;
import com.lab.client.Data.Route;

import java.util.Locale;
import java.util.Scanner;

public class RouteReader {
    private final Scanner scanner;

    public RouteReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Route readRouteFromConsole() {
        return new Route(readName(), readCoordinates(), readFrom(), readTo(), readDistance());
    }

    //public Route readRouteFromScript() {

    //}

    public String readName() {
        System.out.print("Введите название маршрута: ");
        String name = scanner.nextLine();
        while (name == null || name.isEmpty()) {
            System.out.print("Название маршрута не может быть пустым, повторите попытку: ");
            name = scanner.nextLine();
        }
        return name;
    }

    public Coordinates readCoordinates() {
        final int xMaxValue = 412;
        int x;
        long y;
        System.out.print("Введите координату X маршрута: ");
        x = readInt();
        while (x > xMaxValue) {
            System.out.print("Координата X не может быть больше 412, повторите попытку: ");
            x = readInt();
        }
        System.out.print("Введите координату Y маршрута: ");
        y = readLong();
        return new Coordinates(x, y);
    }

    public Location readFrom() {
        System.out.print("Введите координату X точки начала маршрута: ");
        int x = readInt();
        System.out.print("Введите координату Y точки начала маршрута: ");
        int y = readInt();
        System.out.print("Введите координату Z точки начала маршрута: ");
        double z = readDouble();
        System.out.print("Введите название начальной локации маршрута: ");
        String name = scanner.nextLine();
        while (name == null || name.isEmpty()) {
            System.out.print("Имя начальной локации не может быть пустым, повторите попытку: ");
            name = scanner.nextLine();
        }
        return new Location(x, y, z, name);
    }

    public Location readTo() {
        String response;
        while (true) {
            System.out.print("Известна ли конечная точка маршрута? (y/n): ");
            response = scanner.nextLine().toLowerCase(Locale.ROOT);
            if ("n".equals(response)) {
                return null;
            } else if ("y".equals(response)) {
                System.out.print("Введите координату X точки конца маршрута: ");
                int x = readInt();
                System.out.print("Введите координату Y точки конца маршрута: ");
                int y = readInt();
                System.out.print("Введите координату Z точки конца маршрута: ");
                double z = readDouble();
                System.out.print("Введите название конечной локации маршрута: ");
                String name = scanner.nextLine();
                while (name == null || name.isEmpty()) {
                    System.out.print("Имя конечной локации не может быть пустым, повторите попытку: ");
                    name = scanner.nextLine();
                }
                return new Location(x, y, z, name);
            }
        }
    }

    public double readDistance() {
        System.out.print("Введите дистанцию маршрута: ");
        double value = readDouble();
        while (Double.compare(value, 1) <= 0) {
            System.out.print("Дистанция должна быть больше 1, повторите попытку: ");
            value = readDouble();
        }
        return value;
    }

    public int readInt() {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Ошибка при вводе, повторите попытку: ");
            }
        }
        return value;
    }

    public long readLong() {
        long value;
        while (true) {
            try {
                value = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Ошибка при вводе, повторите попытку: ");
            }
        }
        return value;
    }

    public double readDouble() {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException | NullPointerException e) {
                System.out.print("Ошибка при вводе, повторите попытку: ");
            }
        }
        return value;
    }
}

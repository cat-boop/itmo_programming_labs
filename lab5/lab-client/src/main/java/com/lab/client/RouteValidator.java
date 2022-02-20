package com.lab.client;

import com.lab.client.Data.Coordinates;
import com.lab.client.Data.Location;
import com.lab.client.Data.Route;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public final class RouteValidator {
    private static String printIfError;
    private static final Set<Long> ID_SET = new HashSet<>();

    private RouteValidator() {
        throw new UnsupportedOperationException("This class should not be instantiated");
    }

    public static boolean validateRoutes(Route[] routes) {
        boolean flag = true;
        boolean isIdRight;
        boolean isNameRight;
        boolean isCoordinatesRight;
        boolean isCreationDateRight;
        boolean isFromRight;
        boolean isToRight;
        boolean isDistanceRight;
        long maxId = 0;
        for (int i = 0; i < routes.length; i++) {
            printIfError = "Элемент " + (i + 1) + ": ";
            Route currentRoute = routes[i];
            //System.out.println(currentRoute);
            isIdRight = validateId(currentRoute.getId());
            isNameRight = validateName(currentRoute.getName());
            isCoordinatesRight = validateCoordinates(currentRoute.getCoordinates());
            isCreationDateRight = validateCreationDate(currentRoute.getCreationDate());
            isFromRight = validateFrom(currentRoute.getFrom());
            isToRight = validateTo(currentRoute.getTo());
            isDistanceRight = validateDistance(currentRoute.getDistance());
            if (!isIdRight || !isNameRight || !isCoordinatesRight || !isCreationDateRight || !isFromRight || !isToRight || !isDistanceRight) {
                flag = false;
            }
            if (isIdRight) {
                maxId = Math.max(maxId, currentRoute.getId());
            }
        }
        Route.setNextId(maxId + 1);
        return flag;
    }

    private static boolean validateId(Long id) {
        if (id == null) {
            System.out.println(printIfError + "не указан Id");
            return false;
        }
        if (ID_SET.contains(id)) {
            System.out.println(printIfError + "элемент с Id " + id + " уже существует");
            return false;
        }
        ID_SET.add(id);
        return true;
    }

    private static boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println(printIfError + "имя маршрута не может быть пустым");
            return false;
        }
        return true;
    }

    private static boolean validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            System.out.println(printIfError + "координаты маршрута не могут быть null");
            return false;
        }
        boolean flag = true;
        final int xMaxValue = 412;
        int x = coordinates.getX();
        Long y = coordinates.getY();
        if (x > xMaxValue) {
            System.out.println(printIfError + "координата X не может быть больше 412");
            flag = false;
        }
        if (y == null) {
            System.out.println(printIfError + "координата Y не может быть null");
            flag = false;
        }
        return flag;
    }

    private static boolean validateCreationDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            System.out.println(printIfError + "дата создания не может быть null");
            return false;
        }
        return true;
    }

    private static boolean validateFrom(Location location) {
        if (location == null) {
            System.out.println(printIfError + "место начала маршрута не может быть null");
            return false;
        }
        boolean flag = true;
        Integer x = location.getX();
        Double z = location.getZ();
        String name = location.getName();
        if (x == null) {
            System.out.println(printIfError + "координата X начальной локации не может быть null");
            flag = false;
        }
        if (z == null) {
            System.out.println(printIfError + "координата Z начальной локации не может быть null");
            flag = false;
        }
        if (name == null) {
            System.out.println(printIfError + "название начальной локации не может быть пустым или null");
            flag = false;
        }
        return flag;
    }

    private static boolean validateTo(Location location) {
        if (location == null) {
            return true;
        }
        boolean flag = true;
        Integer x = location.getX();
        Double z = location.getZ();
        String name = location.getName();
        if (x == null) {
            System.out.println(printIfError + "координата X конечной локации не может быть null");
            flag = false;
        }
        if (z == null) {
            System.out.println(printIfError + "координата Z конечной локации не может быть null");
            flag = false;
        }
        if (name == null) {
            System.out.println(printIfError + "название конечной локации не может быть пустым или null");
            flag = false;
        }
        return flag;
    }

    private static boolean validateDistance(double distance) {
        if (Double.compare(distance, 1) <= 0) {
            System.out.println(printIfError + "дистанция маршрута должна быть больше 1");
            return false;
        }
        return true;
    }
}

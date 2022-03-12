package com.lab.client.Utility;

import com.lab.client.Data.Coordinates;
import com.lab.client.Data.Location;
import com.lab.client.Data.Route;
import com.lab.client.Exceptions.RouteValidateException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class that validate Route
 * This class should not be instantiated
 */
public final class RouteValidator {
    private static final Set<Long> ID_SET = new HashSet<>();

    private RouteValidator() {
        throw new UnsupportedOperationException("This class should not be instantiated");
    }

    public static void validateRoutes(Route... routes) {
        long maxId = 0;
        for (Route currentRoute : routes) {
            validateId(currentRoute.getId());
            validateName(currentRoute.getName());
            validateCoordinates(currentRoute.getCoordinates());
            validateCreationDate(currentRoute.getCreationDate());
            validateFrom(currentRoute.getFrom());
            validateTo(currentRoute.getTo());
            validateDistance(currentRoute.getDistance());
            maxId = Math.max(maxId, currentRoute.getId());
        }
        Route.setNextId(maxId + 1);
    }

    private static void validateId(Long id) {
        if (id == null) {
            throw new RouteValidateException("Id не может быть null");
        }
        if (ID_SET.contains(id)) {
            throw new RouteValidateException("Id не могут повторяться");
        }
        ID_SET.add(id);
    }

    private static void validateName(String name) {
        if (name == null) {
            throw new RouteValidateException("Имя маршрута не может быть пустым");
        }
    }

    private static void validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new RouteValidateException("Координаты не могут быть null");
        }
        final int xMaxValue = 412;
        if (coordinates.getX() > xMaxValue) {
            throw new RouteValidateException("Координата X маршрута не может быть больше 412");
        }
        if (coordinates.getY() == null) {
            throw new RouteValidateException("Координата Y маршрута не может быть null");
        }
    }

    private static void validateCreationDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new RouteValidateException("Время создания маршрута не может быть null");
        }
    }

    private static void validateFrom(Location location) {
        if (location == null) {
            throw new RouteValidateException("Начальная локация не может быть null");
        }
        if (location.getX() == null) {
            throw new RouteValidateException("Координата X начальной локации не может быть null");
        }
        if (location.getZ() == null) {
            throw new RouteValidateException("Координата Z начальной локации не может быть null");
        }
        if (location.getName() == null) {
            throw new RouteValidateException("Название начальной локации не может быть null");
        }
    }

    private static void validateTo(Location location) {
        if (location == null) {
            return;
        }
        if (location.getX() == null) {
            throw new RouteValidateException("Координата X конечной локации не может быть null");
        }
        if (location.getZ() == null) {
            throw new RouteValidateException("Координата Z конечной локации не может быть null");
        }
        if (location.getName() == null) {
            throw new RouteValidateException("Название конечной локации не может быть null");
        }
    }

    private static void validateDistance(double distance) {
        if (Double.compare(distance, 1) < 0) {
            throw new RouteValidateException("Дистанция маршрута не может быть меньше 1");
        }
    }
}

package com.lab.client.Utility;

import com.lab.client.Data.Coordinates;
import com.lab.client.Data.Location;
import com.lab.client.Data.Route;

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

    //    /**
//     * @param routes input routes that should be validated
//     */
//    public static boolean validateRoutes(Route... routes) {
//        final Map<Long, Integer> idMap = new HashMap<>();
//        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        final Validator validator = factory.getValidator();
//        long maxId = 0;
//        for (Route route : routes) {
//            if (!idMap.containsKey(route.getId())) {
//                idMap.put(route.getId(), 0);
//            } else {
//                idMap.replace(route.getId(), idMap.get(route.getId()), idMap.get(route.getId()) + 1);
//            }
//            Set<ConstraintViolation<Route>> constraintViolations = validator.validate(route);
//            if (idMap.get(route.getId()) > 1 || constraintViolations.size() > 0) {
//                return false;
//            }
//            maxId = Math.max(route.getId(), maxId);
//        }
//        Route.setNextId(maxId);
//        return true;
//    }
    public static boolean validateRoutes(Route... routes) {
        boolean isIdRight;
        boolean isNameRight;
        boolean isCoordinatesRight;
        boolean isCreationDateRight;
        boolean isFromRight;
        boolean isToRight;
        boolean isDistanceRight;
        long maxId = 0;
        for (Route currentRoute : routes) {
            isIdRight = validateId(currentRoute.getId());
            isNameRight = validateName(currentRoute.getName());
            isCoordinatesRight = validateCoordinates(currentRoute.getCoordinates());
            isCreationDateRight = validateCreationDate(currentRoute.getCreationDate());
            isFromRight = validateFrom(currentRoute.getFrom());
            isToRight = validateTo(currentRoute.getTo());
            isDistanceRight = validateDistance(currentRoute.getDistance());
            if (!isIdRight || !isNameRight || !isCoordinatesRight || !isCreationDateRight || !isFromRight || !isToRight || !isDistanceRight) {
                return false;
            }
            maxId = Math.max(maxId, currentRoute.getId());
        }
        Route.setNextId(maxId + 1);
        return true;
    }

    private static boolean validateId(Long id) {
        if (id == null) {
            return false;
        }
        if (ID_SET.contains(id)) {
            return false;
        }
        ID_SET.add(id);
        return true;
    }

    private static boolean validateName(String name) {
        return name != null && !name.isEmpty();
    }

    private static boolean validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            return false;
        }
        final int xMaxValue = 412;
        return coordinates.getX() <= xMaxValue && coordinates.getY() != null;
    }

    private static boolean validateCreationDate(LocalDateTime localDateTime) {
        return localDateTime != null;
    }

    private static boolean validateFrom(Location location) {
        if (location == null) {
            return false;
        }
        return location.getX() != null && location.getZ() != null && location.getName() != null;
    }

    private static boolean validateTo(Location location) {
        if (location == null) {
            return true;
        }
        return location.getX() != null && location.getZ() != null && location.getName() != null;
    }

    private static boolean validateDistance(double distance) {
        return Double.compare(distance, 1) > 0;
    }
}

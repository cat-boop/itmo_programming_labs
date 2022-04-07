package com.lab.server;

import com.lab.common.Data.Route;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Class that manage collection
 */
public class CollectionManager {
    private static Long nextId = 1L;
    private final NavigableSet<Route> routes;
    private final LocalDateTime creationDate;

    public CollectionManager(List<Route> routes) {
        this.routes = new TreeSet<>(Comparator.comparing(Route::getName));
        this.routes.addAll(routes);
        if (routes.size() > 0) {
            nextId = routes.stream().max(Comparator.comparing(Route::getId)).get().getId() + 1;
        }
        creationDate = LocalDateTime.now();
    }

    /**
     * @return current collection
     */
    public NavigableSet<Route> getCollection() {
        return new TreeSet<>(routes);
    }

    /**
     * @return collection creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return collection name
     */
    public String getCollectionName() {
        return routes.getClass().toString();
    }

    /**
     * @return size of collection
     */
    public int getSize() {
        return routes.size();
    }

    /**
     * @param route new Route to add to collection
     * @return true if element successfully added to collection, else false
     */
    public boolean add(Route route) {
        route.setId(nextId++);
        return routes.add(route);
    }

    /**
     * update Route with given id with the data from given route
     * @param id id of route to update
     * @param route route contains data to update
     */
    public void updateById(Long id, Route route) {
        Route routeToUpdate = routes.stream().filter(collectionRoute -> collectionRoute.getId().equals(id)).findAny().orElseThrow(NoSuchElementException::new);
        routeToUpdate.update(route);
    }

    /**
     * if collection contains element with given id, this element will be removed, else collection will not be changed
     */
    public void removeById(Long id) {
        routes.remove(routes.stream().filter(route -> route.getId().equals(id)).findAny().orElseThrow(NoSuchElementException::new));
    }

    /**
     * remove all elements from collection
     */
    public void clear() {
        routes.clear();
    }

    /**
     * add new element to collection if distance of given route less than the minimal distance of routes in collection
     * @param route new route that will be added if condition will be true
     * @return true if element added, false else
     */
    public boolean addIfMin(Route route) {
        boolean success = false;
        if (routes.stream().allMatch((collectionRoute) -> Double.compare(route.getDistance(), collectionRoute.getDistance()) < 0)) {
            route.setId(nextId++);
            success = routes.add(route);
        }
        return success;
    }

    /**
     * remove all routes in collection which distance greater than distance of given route
     */
    public int removeGreater(Route route) {
        Collection<Route> collection = routes.stream().filter(setRoute -> Double.compare(setRoute.getDistance(), route.getDistance()) > 0).collect(Collectors.toList());
        collection.forEach(System.out::println);
        collection.forEach(routes::remove);
        return collection.size();
    }

    /**
     * remove all routes in collection which distance lower than distance of given route
     */
    public int removeLower(Route route) {
        Collection<Route> collection = routes.stream().filter(setRoute -> Double.compare(setRoute.getDistance(), route.getDistance()) < 0).collect(Collectors.toList());
        collection.forEach(routes::remove);
        return collection.size();
    }

    /**
     * @return route from collection with maximum distance
     */
    public Route maxByDistance() {
        return routes.stream().max(Comparator.comparing(Route::getDistance)).orElseThrow(NoSuchElementException::new);
    }

    /**
     * @return number of routes which distance is lower than the given distance
     */
    public int countLessThanDistance(double distance) {
        return (int) routes.stream().filter(route -> Double.compare(route.getDistance(), distance) < 0).count();
    }

    /**
     * @return number of routes which distance is greater than the given distance
     */
    public int countGreaterThanDistance(double distance) {
        return (int) routes.stream().filter(route -> Double.compare(route.getDistance(), distance) > 0).count();
    }
}

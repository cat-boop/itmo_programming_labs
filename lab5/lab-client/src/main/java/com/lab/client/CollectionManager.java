package com.lab.client;

import com.lab.client.Data.Route;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class CollectionManager {
    private final TreeSet<Route> treeSet;
    private final java.time.LocalDateTime creationDate;

    public CollectionManager(ArrayList<Route> arrayList) {
        treeSet = new TreeSet<>(Comparator.comparing(Route::getDistance));
        treeSet.addAll(arrayList);
        creationDate = java.time.LocalDateTime.now();
    }

    public TreeSet<Route> getTreeSet() {
        return new TreeSet<>(treeSet);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCollectionName() {
        return treeSet.getClass().toString();
    }

    public int getSize() {
        return treeSet.size();
    }

    public boolean add(Route route) {
        return treeSet.add(route);
    }

    public boolean existElementWithId(Long id) {
        for (Route setRoute : treeSet) {
            if (setRoute.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean updateById(Long id, Route route) {
        boolean success = false;
        for (Route setRoute : treeSet) {
            if (setRoute.getId().equals(id)) {
                setRoute.update(route);
                success = true;
            }
        }
        return success;
    }

    public boolean removeById(Long id) {
        return treeSet.removeIf(setRoute -> setRoute.getId().equals(id));
    }

    public void clear() {
        treeSet.clear();
    }

    public boolean addIfMin(Route route) {
        boolean success = false;
        if (Double.compare(route.getDistance(), treeSet.first().getDistance()) < 0) {
            success = treeSet.add(route);
        }
        return success;
    }

    public boolean removeGreater(Route route) {
        return treeSet.removeIf(setRoute -> Double.compare(setRoute.getDistance(), route.getDistance()) > 0);
    }

    public boolean removeLower(Route route) {
        return treeSet.removeIf(setRoute -> Double.compare(setRoute.getDistance(), route.getDistance()) < 0);
    }

    public Route maxByDistance() {
        return treeSet.last();
    }

    public int countLessThanDistance(double distance) {
        int countElements = 0;
        for (Route route : treeSet) {
            if (Double.compare(route.getDistance(), distance) < 0) {
                countElements++;
            }
        }
        return countElements;
    }

    public int countGreaterThanDistance(double distance) {
        int countElements = 0;
        for (Route route : treeSet) {
            if (Double.compare(route.getDistance(), distance) > 0) {
                countElements++;
            }
        }
        return countElements;
    }
}

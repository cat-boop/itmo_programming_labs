package com.lab.client.Data;

import java.time.LocalDateTime;

public class Route {
    private static Long nextId = 0L;
    private final Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле не может быть null
    private Location to; //Поле может быть null
    private double distance; //Значение поля должно быть больше 1

    public Route(String name, Coordinates coordinates, Location from, Location to, double distance) {
        id = nextId++;
        creationDate = java.time.LocalDateTime.now();
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public double getDistance() {
        return distance;
    }

    public void update(Route route) {
        name = route.name;
        coordinates = route.coordinates;
        from = route.from;
        to = route.to;
        distance = route.distance;
    }

    @Override
    public String toString() {
        return "Id = " + id + ", name = \"" + name + "\", coordinates = " + coordinates + ", creation date = "
                + creationDate.toString() + ", from = " + from + ", to = " + to + ", distance = " + distance;
    }
}

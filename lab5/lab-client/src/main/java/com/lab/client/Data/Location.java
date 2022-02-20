package com.lab.client.Data;

public class Location {
    private Integer x; //Поле не может быть null
    private int y;
    private Double z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location(Integer x, int y, Double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + ", " + z + ", \"" + name + "\"}";
    }
}


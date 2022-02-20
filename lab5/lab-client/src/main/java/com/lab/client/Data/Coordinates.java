package com.lab.client.Data;

public class Coordinates {
    private int x; //Максимальное значение поля: 412
    private Long y; //Поле не может быть null

    public Coordinates(int x, Long y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }
}

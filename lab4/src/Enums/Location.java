package Enums;

public enum Location {
    ZMEEVKA("Змеевка"),
    PROM("Бал"),
    DANDELION("Одуванчики"),
    PLATFORM("Площадка");

    private final String location;
    Location(String location) {
        this.location = location;
    }

    public String toString() {
        return location;
    }
}

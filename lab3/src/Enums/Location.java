package Enums;

public enum Location {
    ZMEEVKA("Змеевка"),
    PROM("Бал");

    private final String location;
    Location(String location) {
        this.location = location;
    }

    public String toString() {
        return location;
    }
}

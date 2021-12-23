package Enums;

public enum Action {
    SITTING("Действие \"сидит\""),
    RESTING("Действие \"лежит\""),
    SLEEPING("Действие \"спит\"");

    private final String location;
    Action(String location) {
        this.location = location;
    }

    public String toString() {
        return location;
    }
}

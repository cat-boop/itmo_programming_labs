package Exceptions;

public class IllegalFloorException extends Exception {
    private final int numberOfFloor;

    public IllegalFloorException(String s, int numberOfFloor) {
        super(s);
        this.numberOfFloor = numberOfFloor;
    }

    public String getMessage() {
        return super.getMessage() + ": " + numberOfFloor;
    }
}

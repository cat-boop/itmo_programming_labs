package Things;

import Music.Orchestra;

public class Floor {
    private final int numberOfFloor;
    private final Orchestra capacity;

    public Floor(int numberOfFloor, Orchestra capacity) {
        this.numberOfFloor = numberOfFloor;
        this.capacity = capacity;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public Orchestra getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "На " + numberOfFloor + " этаже находится Объект " + capacity;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numberOfFloor) + capacity.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Floor)) return false;

        Floor floor = (Floor) object;
        return numberOfFloor == floor.numberOfFloor && capacity.equals(floor.capacity);
    }
}

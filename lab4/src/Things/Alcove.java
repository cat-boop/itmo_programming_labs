package Things;

import Enums.Decoration;
import Exceptions.IllegalFloorException;
import Music.Orchestra;

import java.util.Arrays;

public class Alcove implements ThingInterface {
    private final int numberOfFloors;
    private final Floor[] floors;
    private final Decoration decoration;
    private final String name = "Беседка";

    public static class Floor {
        private final int numberOfFloor;
        private final Orchestra capacity;

        public Floor(int numberOfFloor, Orchestra capacity) {
            if (capacity == null) {
                throw new IllegalArgumentException("Аргумент не может быть null");
            }
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
            if (this == object) return true;
            if (!(object instanceof Alcove.Floor)) return false;

            Alcove.Floor floor = (Alcove.Floor) object;
            return numberOfFloor == floor.numberOfFloor && capacity.equals(floor.capacity);
        }
    }

    public Alcove(int numberOfFloors, Decoration decoration, Floor... floors) throws IllegalFloorException {
        if (numberOfFloors < 1) {
            throw new IllegalFloorException("Количество этажей не может быть меньше 1", numberOfFloors);
        }
        for (Floor floor : floors) {
            if (floor.numberOfFloor < 1 || floor.numberOfFloor > numberOfFloors) {
                throw new IllegalFloorException("Неверный номер этажа", floor.numberOfFloor);
            }
        }
        if (decoration == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }

        this.numberOfFloors = numberOfFloors;
        this.floors = floors;
        this.decoration = decoration;
    }

    public String analyzeDecoration() {
        if (decoration == Decoration.DEFAULT) return "выглядит обычно";
        return "украшена Объектом " + decoration;
    }

    public void join() {
        System.out.println("Объект " + name + ", которая " + analyzeDecoration() + ", имеет " + numberOfFloors + " этажа.");
        System.out.println("При этом:");
        for (Floor floor : floors) System.out.println(floor);
        System.out.println();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Объект " + name + " с " + numberOfFloors + " этажами.";
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numberOfFloors) + Arrays.hashCode(floors) + decoration.hashCode()
                + name.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Alcove)) return false;

        Alcove alcove = (Alcove) object;
        return numberOfFloors == alcove.numberOfFloors && Arrays.equals(floors, alcove.floors)
                && decoration.equals(alcove.decoration);
    }
}

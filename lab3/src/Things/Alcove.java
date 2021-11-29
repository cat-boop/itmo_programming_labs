package Things;

import Enums.Decoration;

import java.util.Arrays;

public class Alcove implements ThingInterface {
    private final int numberOfFloors;
    private final Floor[] floors;
    private final Decoration decoration;
    private final String name = "Беседка";

    public Alcove(int numberOfFloors, Decoration decoration, Floor... floors) {
        this.numberOfFloors = numberOfFloors;
        this.floors = floors;
        this.decoration = decoration;
    }

    public String getDecoration() {
        if (decoration == Decoration.DEFAULT) return "выглядит обычно";
        return "украшена Объектом " + decoration;
    }

    public void join() {
        System.out.println("Объект " + name + ", которая " + getDecoration() + ", имеет " + numberOfFloors + " этажа.");
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

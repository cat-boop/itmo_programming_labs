import Enums.Decoration;
import Interfaces.ThingInterface;

public class Alcove implements ThingInterface {
    private final int numberOfFloors;
    private final CapacityOnFloor capacityOnFloor;
    private final Decoration decoration;
    private final String name = "\"Беседка\"";

    public Alcove(int numberOfFloors, CapacityOnFloor capacityOnFloor, Decoration decoration) {
        this.numberOfFloors = numberOfFloors;
        this.capacityOnFloor = capacityOnFloor;
        this.decoration = decoration;
    }

    public String getDecoration() {
        if (decoration == Decoration.DEFAULT) return "выглядит обычно";
        return "украшена Объектом " + decoration.getName();
    }

    public String getCapacityOnFloor() {
        int numberOfFloor = capacityOnFloor.getFloor();
        int numberOfCharacters = capacityOnFloor.getNumberOfCharacters();
        Object character = capacityOnFloor.getCharacter();
        return "на " + numberOfFloor + " этаже Обьекта " + name + " вмещается " +
                numberOfCharacters + " Персонажей " + character.toString() + ".";
    }

    public void join() {
        System.out.println("Объект " + name + ", которая " + getDecoration() + ", имеет " + numberOfFloors + " этажа.");
        System.out.println("При этом, " + getCapacityOnFloor() + "\n");
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
        return Integer.hashCode(numberOfFloors) + capacityOnFloor.hashCode() + decoration.hashCode()
                + name.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Alcove)) return false;

        Alcove alcove = (Alcove) object;
        return numberOfFloors == alcove.numberOfFloors && capacityOnFloor.equals(alcove.capacityOnFloor)
                && decoration.equals(alcove.decoration);
    }
}

package Things;

public class CapacityOnFloor {
    private final int floor;
    private final int numberOfCharacters;
    private final Object character;

    public CapacityOnFloor(int floor, int capacity, Object characterAtFloor) {
        this.floor = floor;
        this.numberOfCharacters = capacity;
        this.character = characterAtFloor;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public Object getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "На " + floor + " этаже находится " + numberOfCharacters + " Персонажей " + character.toString();
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(floor).hashCode() + Integer.valueOf(numberOfCharacters).hashCode() + character.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof CapacityOnFloor)) return false;

        CapacityOnFloor capacityOnFloor = (CapacityOnFloor) object;
        return floor == capacityOnFloor.floor && numberOfCharacters == capacityOnFloor.numberOfCharacters
                && character.equals(capacityOnFloor.character);
    }
}

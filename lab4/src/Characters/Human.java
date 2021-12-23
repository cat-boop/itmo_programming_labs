package Characters;

import Things.ThingInterface;

public abstract class Human implements ThingInterface {
    private final String abstractName;

    protected Human(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }
        this.abstractName = name;
    }

    @Override
    public String getName() {
        return abstractName;
    }
}

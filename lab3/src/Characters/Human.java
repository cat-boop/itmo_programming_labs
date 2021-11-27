package Characters;

import Things.ThingInterface;

public abstract class Human implements ThingInterface {
    private final String abstractName;

    protected Human(String name) {
        this.abstractName = name;
    }

    @Override
    public String getName() {
        return abstractName;
    }
}

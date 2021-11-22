package Characters;

import Things.Harp;

public class Baby extends AbstractHuman {
    private static final String DEFAULT_NAME = "Малышка";

    public Baby() {
        super(DEFAULT_NAME);
    }

    public Baby(String name) {
        super(DEFAULT_NAME + " " + name);
    }

    public void playOn(Harp harp) {
        System.out.println("Каждый Персонаж " + getName() + " играл на Объекте " + harp.getName() + ".");
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        return object instanceof Baby;
    }
}

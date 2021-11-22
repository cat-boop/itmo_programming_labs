import AbstractClasses.AbstractHuman;

public class Baby extends AbstractHuman {

    private final String name = "\"Малышка\"";

    @Override
    public String getName() {
        return name;
    }

    public void playOn(Harp harp) {
        System.out.println("Каждый Персонаж " + name + " играл на Объекте " + harp.getName() + ".");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        return object instanceof Baby;
    }
}

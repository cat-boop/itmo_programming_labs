package Characters;

import Enums.Appearance;

import java.util.Arrays;

public class Visitor extends AbstractHuman {
    private final String city;
    private final Visitor comeAfter;
    private final Appearance[] appearances;

    public Visitor(String name, String city) {
        this(name, city, null, new Appearance[0]);
    }

    public Visitor(String name, String city, Appearance... appearances) {
        this(name, city, null, appearances);
    }

    public Visitor(String name, String city, Visitor comeAfter, Appearance... appearances) {
        super(name);
        this.city = city;
        this.comeAfter = comeAfter;
        this.appearances = appearances;
    }

    public void come() {
        System.out.print(this + ", приехал ");
        if (comeAfter == null) System.out.println("первым.");
        else System.out.println("после Персонажа " + comeAfter.getName() + ".");
    }


    public void analyzeAppearance() {
        if (appearances.length == 0 || (appearances.length == 1 && appearances[0] == Appearance.DEFAULT))
            System.out.print("Про внешний вид Персонажа " + getName() + " ничего не известно.");
        else {
            System.out.print("Внешний вид Персонажа " + getName() + ": ");
            for (Appearance appearance : appearances) System.out.print(appearance.getAppearance() + "; ");
        }
        System.out.println("\n");
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Персожан с именем " + getName() + ", родом из города " + city;
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + city.hashCode() + (comeAfter != null ? comeAfter.hashCode() : 0)
                + Arrays.hashCode(appearances);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Visitor)) return false;

        Visitor visitor = (Visitor) object;
        return getName().equals(visitor.getName()) && city.equals(visitor.city) && comeAfter.equals(visitor.comeAfter)
                && Arrays.equals(appearances, visitor.appearances);
    }
}

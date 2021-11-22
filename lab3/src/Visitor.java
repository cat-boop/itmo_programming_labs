import AbstractClasses.AbstractHuman;
import Enums.Appearance;

import java.util.Arrays;

public class Visitor extends AbstractHuman {
    private final String name;
    private final String city;
    private final Visitor comeAfter;
    private final Appearance[] appearances;

    public Visitor(String name, String city) {
        this.name = "\"" + name + "\"";
        this.city = city;
        this.comeAfter = null;
        this.appearances = new Appearance[0];
    }

    public Visitor(String name, String city, Appearance... appearances) {
        this.name = "\"" + name + "\"";
        this.city = city;
        this.comeAfter = null;
        this.appearances = appearances;
    }

    public Visitor(String name, String city, Visitor comeAfter, Appearance... appearances) {
        this.name = "\"" + name + "\"";
        this.city = city;
        this.comeAfter = comeAfter;
        this.appearances = appearances;
    }

    public void come() {
        System.out.print(this + ", приехал ");
        if (comeAfter == null) System.out.println("первым.");
        else System.out.println("после Персонажа " + comeAfter.name + ".");
    }


    public void analyzeAppearance() {
        if (appearances.length == 1 && appearances[0] == Appearance.DEFAULT)
            System.out.print("Про внешний вид Персонажа " + name + " ничего не известно.");
        else {
            System.out.print("Внешний вид Персонажа " + name + ": ");
            for (Appearance appearance : appearances) System.out.print(appearance.getAppearance() + "; ");
        }
        System.out.println("\n");
    }

    public String getCity() {
        return city;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Персожан с именем " + name + ", родом из города " + city;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + city.hashCode() + comeAfter.hashCode() + Arrays.hashCode(appearances);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Visitor)) return false;

        Visitor visitor = (Visitor) object;
        return name.equals(visitor.name) && city.equals(visitor.city) && comeAfter.equals(visitor.comeAfter)
                && Arrays.equals(appearances, visitor.appearances);
    }
}

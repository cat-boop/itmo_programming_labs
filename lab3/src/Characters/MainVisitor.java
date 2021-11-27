package Characters;

import Enums.Appearance;
import Things.Shirt;

import java.util.Arrays;

public class MainVisitor extends Visitor {
    private Appearance[] appearances;
    private Shirt shirt;

    public MainVisitor(String name, String city) {
        super(name, city);
    }

    public MainVisitor(String name, String city, boolean wasInvited) {
        super(name, city, wasInvited, new Visitor[0]);
    }

    public MainVisitor(String name, String city, boolean invited, Visitor... comeAfter) {
        super(name, city, invited, comeAfter);
    }

    public MainVisitor(String name, String city, boolean invited, Shirt shirt, Appearance... appearances) {
        super(name, city, invited);
        this.shirt = shirt;
        this.appearances = appearances;
    }

    public void analyzeAppearance() {
        System.out.print("Внешний вид персонажа: ");
        if (appearances == null && shirt == null) System.out.print("Ничем не примечательный");
        else {
            if (shirt != null) {
                System.out.print(shirt.getCharacteristic() + "; ");
            }
            if (appearances != null) {
                for (Appearance appearance : appearances) System.out.print(appearance.getCharacteristic() + "; ");
            }
        }
        System.out.println();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (appearances.length != 0 ? Arrays.hashCode(appearances) : 0) +
                (shirt != null ? shirt.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Visitor)) return false;

        MainVisitor mainVisitor = (MainVisitor) object;
        return super.equals(object) && Arrays.equals(appearances, mainVisitor.appearances) &&
                (shirt == null || shirt.equals(mainVisitor.shirt));
    }
}

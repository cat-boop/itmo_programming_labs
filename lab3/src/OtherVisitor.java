import AbstractClasses.AbstractHuman;

import java.util.Arrays;

public class OtherVisitor extends AbstractHuman {
    private final String name;
    private final String city;
    private final boolean wasInvited;
    private final Visitor[] comeAfter;

    public OtherVisitor(String name, String city, boolean wasInvited, Visitor... comeAfter) {
        this.name = "\"" + name + "\"";
        this.city = city;
        this.wasInvited = wasInvited;
        this.comeAfter = comeAfter;
    }

    public void come() {
        System.out.print(this + ", стал появляться после Персонажей: ");
        for (Visitor visitor : comeAfter) {
            String additional = ", ";
            if (visitor.equals(comeAfter[comeAfter.length - 1])) additional = ".";
            System.out.print(visitor.getName() + additional);
        }
        System.out.println();
    }

    public void giveThanks() {
        Baby baby = new Baby();
        if (wasInvited) {
            System.out.println("Хоть Персонаж " + name + " и был приглашен, он все равно благодарил Персонажа " +
                    baby.getName() + " за фрукты.");
        } else {
            System.out.println("Хоть Персонаж " + name + " и не был приглашен, но он говорил, " +
                    "что приехал поблагодарить Персонажа " + baby.getName() + " за фрукты.");
        }
    }

    public void getInvite() {
        System.out.println("Персонаж " + name + " получал приглашение остаться на бал.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Персонаж с именем " + name + ", родом из города " + city;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + city.hashCode() + Boolean.hashCode(wasInvited) + Arrays.hashCode(comeAfter);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OtherVisitor)) return false;

        OtherVisitor otherVisitor = (OtherVisitor) object;
        return name.equals(otherVisitor.name) && city.equals(otherVisitor.city) &&
                (wasInvited == otherVisitor.wasInvited) && Arrays.equals(comeAfter, otherVisitor.comeAfter);
    }
}

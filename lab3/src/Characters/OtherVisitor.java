package Characters;

import java.util.Arrays;

public class OtherVisitor extends AbstractHuman {
    private final String city;
    private final boolean wasInvited;
    private final Visitor[] comeAfter;

    public OtherVisitor(String name, String city, boolean wasInvited, Visitor... comeAfter) {
        super(name);
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
            System.out.println("Хоть Персонаж " + getName() + " и был приглашен, он все равно благодарил Персонажа " +
                    baby.getName() + " за фрукты.");
        } else {
            System.out.println("Хоть Персонаж " + getName() + " и не был приглашен, но он говорил, " +
                    "что приехал поблагодарить Персонажа " + baby.getName() + " за фрукты.");
        }
    }

    public void getInvite() {
        System.out.println("Персонаж " + getName() + " получил приглашение остаться на бал.");
    }

    @Override
    public String toString() {
        return "Персонаж с именем " + getName() + ", родом из города " + city;
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + city.hashCode() + Boolean.hashCode(wasInvited) + Arrays.hashCode(comeAfter);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OtherVisitor)) return false;

        OtherVisitor otherVisitor = (OtherVisitor) object;
        return getName().equals(otherVisitor.getName()) && city.equals(otherVisitor.city) &&
                (wasInvited == otherVisitor.wasInvited) && Arrays.equals(comeAfter, otherVisitor.comeAfter);
    }
}

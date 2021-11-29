package Characters;

import Enums.Appearance;
import Enums.Location;
import Things.Shirt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Visitor extends Human {
    private Location location;
    private final Visitor[] comeAfter;
    private boolean invited;
    private int attemptsToGetToProm = 0;
    private Appearance[] appearances;
    private Shirt shirt;

    public Visitor(String name, Location location) {
        this(name, location, false);
    }

    public Visitor(String name, Location location, boolean wasInvited) {
        this(name, location, wasInvited, new Visitor[0]);
    }

    public Visitor(String name, Location location, boolean wasInvited, Visitor... comeAfter) {
        super(name);
        this.location = location;
        this.invited = wasInvited;
        this.comeAfter = comeAfter;
    }

    public void analyzeAppearance() {
        System.out.print("Внешний вид персонажа: ");
        if (appearances == null && shirt == null) System.out.print("Ничем не примечательный");
        else {
            if (shirt != null) {
                System.out.print(shirt.getCharacteristic() + "; ");
            }
            if (appearances != null) {
                for (Appearance appearance : appearances) System.out.print(appearance + "; ");
            }
        }
        System.out.println();
    }

    public void appear() {
        if (comeAfter.length == 0) System.out.print(this + " приехал первый.");
        else {
            System.out.print(this + ", стал появляться после Персонажей: ");
            for (Visitor visitor : comeAfter) {
                String additional = ", ";
                if (visitor.equals(comeAfter[comeAfter.length - 1])) additional = ".";
                System.out.print(visitor.getName() + additional);
            }
        }
        System.out.println();
    }

    public boolean comeTo(Location location) {
        attemptsToGetToProm++;
        System.out.print(this + ", пытается " + attemptsToGetToProm + " раз попасть на " + location + ", ");
        if (!isInvited()) {
            System.out.println("но у него нет приглашения. Что же персонаж предпримет?");
            return false;
        }
        if (isInvited() && attemptsToGetToProm == 1) {
            this.location = location;
            System.out.println("и так как у него есть приглашение, он остается.");
        }
        if (isInvited() && attemptsToGetToProm > 1) {
            this.location = location;
            System.out.println("однако теперь у него есть приглашение, " + "поэтому он остается!");
        }
        System.out.println();
        return true;
    }

    public void sayThanksTo(Location location, Human... humans) {
        Map<Human, Integer> map = new HashMap<>();
        for (Human human : humans) {
            if (map.containsKey(human)) {
                map.replace(human, map.get(human) + 1);
            } else {
                map.put(human, 1);
            }
        }

        System.out.println(this + ", благодарит:");
        for (Map.Entry<Human, Integer> item : map.entrySet()) {
            System.out.println(item.getValue() + " Персонажей " + item.getKey().getName());
        }
        System.out.println(this + ", получает приглашение остаться на " + location + "!");
        setInvited(true);
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    public boolean isInvited() {
        return invited;
    }

    public void setAppearance(Shirt shirt, Appearance... appearances) {
        this.shirt = shirt;
        this.appearances = appearances;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Персонаж с именем " + getName() + ", родом из города " + location;
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + location.hashCode() + (comeAfter != null ? Arrays.hashCode(comeAfter) : 0)
                + Boolean.hashCode(invited);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Visitor)) return false;

        Visitor visitor = (Visitor) object;
        return getName().equals(visitor.getName()) && location.equals(visitor.location) && Arrays.equals(comeAfter, visitor.comeAfter)
                && (attemptsToGetToProm == visitor.attemptsToGetToProm) && (invited == visitor.invited)
                && Arrays.equals(appearances, visitor.appearances) && (shirt == null || shirt.equals(visitor.shirt));
    }
}

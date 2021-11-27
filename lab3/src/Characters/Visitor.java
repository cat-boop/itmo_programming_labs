package Characters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Visitor extends Human {
    private final String city;
    private final Visitor[] comeAfter;
    private boolean invited;
    private int attemptsToGetToProm = 0;

    public Visitor(String name, String city) {
        this(name, city, false);
    }

    public Visitor(String name, String city, Visitor... comeAfter) {
        this(name, city, false, comeAfter);
    }

    public Visitor(String name, String city, boolean wasInvited) {
        this(name, city, wasInvited, new Visitor[0]);
    }

    public Visitor(String name, String city, boolean wasInvited, Visitor... comeAfter) {
        super(name);
        this.city = city;
        this.invited = wasInvited;
        this.comeAfter = comeAfter;
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

    public boolean comeToProm() {
        attemptsToGetToProm++;
        if (!isInvited()) {
            System.out.println(this + ", пытается попасть на бал, "
                    + "но у него нет приглашения. Что же персонаж предпримет?");
            return false;
        }
        if (isInvited() && attemptsToGetToProm == 1) {
            System.out.println(this + ", пытается попасть на бал, и так как у него есть приглашение, он остается.");
        }
        if (isInvited() && attemptsToGetToProm > 1) {
            System.out.println(this + ", опять пытается попасть на бал, однако теперь у него есть приглашение, " +
                    "поэтому он остается!");
        }
        System.out.println();
        return true;
    }

    public void sayThanksTo(Human... humans) {
        Map<Human, Integer> map = new HashMap<>();
        for (Human human : humans) {
            if (map.containsKey(human)) {
                map.replace(human, map.get(human) + 1);
            }
            else {
                map.put(human, 1);
            }
        }

        System.out.println(this + ", благодарит:");
        for (Map.Entry<Human, Integer> item : map.entrySet()) {
            System.out.println(item.getValue() + " Персонажей " + item.getKey().getName());
        }
        System.out.println(this + ", получает приглашение остаться на бал!");
        setInvited(true);
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    public boolean isInvited() {
        return invited;
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
        return getName().hashCode() + city.hashCode() + (comeAfter != null ? Arrays.hashCode(comeAfter) : 0)
                + Boolean.hashCode(invited);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) return false;

        Visitor visitor = (Visitor) object;
        return getName().equals(visitor.getName()) && city.equals(visitor.city) &&
                Arrays.equals(comeAfter, visitor.comeAfter) && (invited == visitor.invited);
    }
}

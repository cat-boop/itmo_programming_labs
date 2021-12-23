package Characters;

import Enums.Appearance;
import Enums.Location;
import Things.Shirt;

import java.util.Arrays;

public class Visitor extends Human {
    private Location location;
    private final Visitor[] comeAfter;
    private Outlook outlook = new Outlook();
    private boolean invited;
    private int attemptsToGetToProm = 0;

    public class Outlook {
        private Appearance[] appearances;
        private Shirt shirt;

        public Outlook() {
            this(null);
        }

        public Outlook(Shirt shirt, Appearance... appearances) {
            this.shirt = shirt;
            this.appearances = appearances;
        }

        private void setAppearances(Appearance[] appearances) {
            this.appearances = appearances;
        }

        private void setShirt(Shirt shirt) {
            this.shirt = shirt;
        }

        private void analyzeOutlook() {
            System.out.print("Внешний вид персонажа: ");
            if ((appearances == null || appearances.length == 0) && shirt == null) System.out.print("Ничем не примечательный");
            else {
                if (shirt != null) {
                    System.out.print(shirt.getCharacteristic() + "; ");
                }
                if (appearances != null && appearances.length != 0) {
                    for (Appearance appearance : appearances) System.out.print(appearance + "; ");
                }
            }
            System.out.println();
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof Outlook)) return false;
            Outlook outlook = (Outlook) object;
            return Arrays.equals(appearances, outlook.appearances) && shirt.equals(outlook.shirt);
        }

        @Override
        public int hashCode() {
            return shirt.hashCode() + Arrays.hashCode(appearances);
        }
    }

    public Visitor(String name, Location location) {
        this(name, location, false);
    }

    public Visitor(String name, Location location, boolean wasInvited) {
        this(name, location, wasInvited, new Visitor[0]);
    }

    public Visitor(String name, Location location, boolean wasInvited, Visitor... comeAfter) {
        super(name);
        if (location == null || comeAfter == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }
        this.location = location;
        this.invited = wasInvited;
        this.comeAfter = comeAfter;
    }

    public void setAppearances(Appearance... appearances) {
        outlook.setAppearances(appearances);
    }

    public void setShirt(Shirt shirt) {
        outlook.setShirt(shirt);
    }

    public void analyzeOutlook() {
        outlook.analyzeOutlook();
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
            System.out.println("однако теперь у него есть приглашение, поэтому он остается!");
        }
        System.out.println();
        return true;
    }

    public void sayThanksTo(Location location, Human... humans) {
        System.out.println(this + ", благодарит:");
        for (Human human : humans) {
            System.out.println("Персонажа " + human.getName());
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

    public void setOutlook(Outlook outlook) {
        if (outlook == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }
        this.outlook = outlook;
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
        return getName().equals(visitor.getName()) && location.equals(visitor.location) &&
                Arrays.equals(comeAfter, visitor.comeAfter) && (attemptsToGetToProm == visitor.attemptsToGetToProm) &&
                (invited == visitor.invited) && outlook.equals(visitor.outlook);
    }
}

package Characters;

public class Citizen extends AbstractHuman {
    private final Visitor visitor;

    public Citizen(String name, Visitor visitor) {
        super(name);
        this.visitor = visitor;
    }

    public void come() {
        System.out.println("Вечер ещё не наступил, но все Персонажи " + getName() + " ждали Персонажей " + visitor.getName()
                + " из города " + visitor.getCity() + ".");
    }

    @Override
    public String toString() {
        return "Персонаж " + getName() + ", который ждет Персонажа " + visitor.getName();
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + visitor.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Citizen)) return false;

        Citizen citizen = (Citizen) object;
        return getName().equals(citizen.getName()) && visitor.equals(citizen.visitor);
    }
}

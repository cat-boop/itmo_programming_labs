package Characters;

public class Citizen extends Human {
    public Citizen(String name) {
        super(name);
    }

    public void comeAndWaitFor(Human human) {
        String additional = "";
        if (human instanceof Visitor) additional = " из города " + ((Visitor) human).getCity();
        System.out.println("Вечер ещё не наступил, но все Персонажи " + getName() + " ждали Персонажей " +
                human.getName() + additional + ".");
    }

    @Override
    public String toString() {
        return "Персонаж " + getName();
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Citizen)) return false;

        Citizen citizen = (Citizen) object;
        return getName().equals(citizen.getName());
    }
}

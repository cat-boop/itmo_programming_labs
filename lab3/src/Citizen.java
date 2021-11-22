import AbstractClasses.AbstractHuman;

public class Citizen extends AbstractHuman {
    private final String name;
    private final Visitor visitor;

    public Citizen(String name, Visitor visitor) {
        this.name = "\"" + name + "\"";
        this.visitor = visitor;
    }

    public void come() {
        System.out.println("Вечер ещё не наступил, но все Персонажи " + name + " ждали Персонажей " + visitor.getName()
        + " из города " + visitor.getCity() + ".");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }
}

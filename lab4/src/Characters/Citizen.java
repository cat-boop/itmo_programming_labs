package Characters;

public class Citizen extends Human {
    public Citizen(String name) {
        super(name);
        if (name == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }
    }

    public void comeAndWaitFor(Human human) {
        HumanAnalyzerInterface humanAnalyzer = new HumanAnalyzerInterface() {
            @Override
            public String analyzeHuman(Human human) {
                String additional = "";
                if (human instanceof Visitor) additional = " из города " + ((Visitor) human).getLocation();
                return "Вечер ещё не наступил, но все Персонажи " + getName() + " ждали Персонажей " +
                        human.getName() + additional + ".";
            }
        };
        System.out.println(humanAnalyzer.analyzeHuman(human));
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

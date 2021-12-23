package Characters;

import Enums.Location;

public class Citizen extends Human {
    private Location location;

    public Citizen(String name, Location location) {
        super(name);
        if (location == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }
        this.location = location;
    }

    public void comeAndWaitFor(Human human) {
        HumanAnalyzerInterface humanAnalyzer = (humanToAnalyze) -> {
            String additional = "";
            if (humanToAnalyze instanceof Visitor) additional = " из города " + ((Visitor) humanToAnalyze).getLocation();
            return "Вечер ещё не наступил, но все Персонажи " + getName() + " находились по местоположению " +
                    location + " и ждали Персонажей " +
                    humanToAnalyze.getName() + additional + ".";
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

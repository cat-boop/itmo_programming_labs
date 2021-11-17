package pokemons;

import moves.Agility;

public class Sealeo extends Spheal {
    public Sealeo(String name, int level) {
        super(name, level);
        setStats(90, 60, 70, 75, 70, 45);
        addMove(new Agility());
    }
}

package pokemons;

import moves.WillOWisp;

public class Walrein extends Sealeo {
    public Walrein(String name, int level) {
        super(name, level);
        setStats(110, 80, 90, 95, 90, 65);
        addMove(new WillOWisp());
    }
}

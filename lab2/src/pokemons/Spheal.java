package pokemons;

import moves.DoubleTeam;
import moves.Rest;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Spheal extends Pokemon {
    public Spheal(String name, int level) {
        super(name, level);
        setStats(70, 40, 50, 55, 50, 25);
        setType(Type.ICE, Type.WATER);
        setMove(new DoubleTeam(), new Rest());
    }
}

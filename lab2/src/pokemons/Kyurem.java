package pokemons;

import moves.AquaTail;
import moves.Confide;
import moves.Crunch;
import moves.WorkUp;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Kyurem extends Pokemon {
    public Kyurem(String name, int level) {
        super(name, level);
        setStats(125, 130, 90, 130, 90, 95);
        setType(Type.DRAGON, Type.ICE);
        setMove(new Confide(), new WorkUp(), new Crunch(), new AquaTail());
    }
}

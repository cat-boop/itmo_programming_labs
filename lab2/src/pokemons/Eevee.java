package pokemons;

import moves.DoubleTeam;
import moves.Facade;
import moves.Screech;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Eevee extends Pokemon {
    public Eevee(String name, int level) {
        super(name, level);
        setStats(55, 55, 50, 45, 65, 55);
        setType(Type.NORMAL);
        setMove(new DoubleTeam(), new Screech(), new Facade());
    }
}

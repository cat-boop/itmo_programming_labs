package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class AquaTail extends PhysicalMove {
    public AquaTail() {
        super(Type.WATER, 90, 90);
    }

    @Override
    protected String describe() {
        return "using Aqua Tail";
    }
}

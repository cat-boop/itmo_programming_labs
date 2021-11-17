package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Type;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        Status status = pokemon.getCondition();
        if (status.equals(Status.BURN) || status.equals(Status.POISON) || status.equals(Status.PARALYZE)) {
            super.power = 140;
        } else {
            super.power = 70;
        }
    }
}

package moves;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        int hp = (int) pokemon.getStat(Stat.HP);
        pokemon.addEffect(new Effect().turns(0).stat(Stat.HP, -hp));
        pokemon.setCondition(new Effect().turns(2).condition(Status.SLEEP));
    }

    @Override
    protected String describe() {
        return "using Rest";
    }
}

import pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();

        //first team
        Kyurem kyurem = new Kyurem("Kyurem", 5);
        Eevee eevee = new Eevee("Eevee", 3);
        Sylveon sylveon = new Sylveon("Sylveon", 2);

        //second team
        Spheal spheal = new Spheal("Spheal", 1);
        Sealeo sealeo = new Sealeo("Sealeo", 1);
        Walrein walrein = new Walrein("Walrein", 3);

        b.addAlly(kyurem);
        b.addAlly(eevee);
        b.addAlly(sylveon);

        b.addFoe(spheal);
        b.addFoe(sealeo);
        b.addFoe(walrein);

        b.go();
    }
}
package Characters;

import Music.InstrumentSize;
import Music.MusicalInstrument;
import Music.Musician;

public class Baby extends Human implements Musician {
    private final static int HEIGHT_FOR_HUGE_HARP = 160;
    private MusicalInstrument musicalInstrument;
    private final int height;
    private boolean stayingOnLadder;

    public Baby(String name, int height, MusicalInstrument musicalInstrument) {
        this(name, height, false, musicalInstrument);
    }

    public Baby(String name, int height, boolean stayingOnLadder, MusicalInstrument musicalInstrument) {
        super(name);
        this.height = height;
        this.stayingOnLadder = stayingOnLadder;

        if (musicalInstrument.getInstrumentSize() == InstrumentSize.HUGE &&
                height < HEIGHT_FOR_HUGE_HARP && !stayingOnLadder) {
            System.out.println("! Рост Персонажа " + name + " не позволяет играть на огромном инструменте !");
            System.out.println("! Измените инструмент через метод setMusicalInstrument или пододвиньте лестницу с " +
                    " помощью метода setStayingOnLadder !");
        }
        this.musicalInstrument = musicalInstrument;
    }

    @Override
    public String playOnInstrumentAndGetSound() {
        if (musicalInstrument.getInstrumentSize() == InstrumentSize.HUGE &&
                height < HEIGHT_FOR_HUGE_HARP && !stayingOnLadder) {
            System.out.println("! Напонимаем, что в данный момент у Персонажа " + this +
                    " в собственности инструмент, не подходящий ему по размеру !");
            System.out.println("! Измените инструмент через метод setMusicalInstrument или пододвиньте лестницу с " +
                    " помощью метода setStayingOnLadder !");
            return null;
        }
        System.out.println("Персонаж " + this + " играет на Объекте " + musicalInstrument.getName()
                + ", который имеет характеристику: " + musicalInstrument.getInstrumentSize().toString() +
                " и издает звуки: " + musicalInstrument.getSound());
        return musicalInstrument.getSound();
    }

    public void setStayingOnLadder(boolean stayingOnLadder) {
        this.stayingOnLadder = stayingOnLadder;
    }

    @Override
    public void setMusicalInstrument(MusicalInstrument musicalInstrument) {
        this.musicalInstrument = musicalInstrument;
    }

    @Override
    public MusicalInstrument getMusicalInstrument() {
        return musicalInstrument;
    }

    @Override
    public String toString() {
        return getName() + " с ростом " + height;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Baby)) return false;

        Baby baby = (Baby) object;
        return getName().equals(baby.getName()) && musicalInstrument.equals(baby.musicalInstrument);
    }
}

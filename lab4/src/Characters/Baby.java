package Characters;

import Exceptions.InstrumentNotAllowedForMusicianException;
import Music.InstrumentSize;
import Music.MusicalInstrument;
import Music.Musician;

public class Baby extends Human implements Musician {
    private MusicalInstrument musicalInstrument;
    private final int height;
    private boolean stayingOnLadder;
    public static boolean isMeeting = false;

    public Baby(String name, int height, MusicalInstrument musicalInstrument) {
        this(name, height, false, musicalInstrument);
    }

    public Baby(String name, int height, boolean stayingOnLadder, MusicalInstrument musicalInstrument) {
        super(name);
        if (musicalInstrument == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }
        if (musicalInstrument.getInstrumentSize() == InstrumentSize.HUGE &&
                height < Musician.HEIGHT_FOR_HUGE_INSTRUMENT && !stayingOnLadder) {
            throw new InstrumentNotAllowedForMusicianException(name, height);
        }
        this.height = height;
        this.stayingOnLadder = stayingOnLadder;
        this.musicalInstrument = musicalInstrument;
    }

    public static void startMeeting() {
        isMeeting = true;
    }

    @Override
    public String playOnInstrumentAndGetSound() {
        System.out.println("Персонаж " + this + " играет на Объекте " + musicalInstrument.getName()
                + ", который имеет характеристику: " + musicalInstrument.getInstrumentSize().toString() +
                " и издает звуки: " + musicalInstrument.getSound());
        return musicalInstrument.getSound();
    }

    public void setStayingOnLadder(boolean stayingOnLadder) {
        this.stayingOnLadder = stayingOnLadder;
    }

    @Override
    public boolean isStayingOnLadder() {
        return stayingOnLadder;
    }

    @Override
    public int getHeight() {
        return height;
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

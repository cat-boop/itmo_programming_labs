package Music;

import Things.ThingInterface;

public class MusicalInstrument implements ThingInterface {
    private final String name;
    private final String sound;
    private final InstrumentSize instrumentSize;

    public MusicalInstrument(String name, String sound, InstrumentSize instrumentSize) {
        if (name == null || sound == null || instrumentSize == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }
        this.name = name;
        this.sound = sound;
        this.instrumentSize = instrumentSize;
    }

    public void join() {
        System.out.println("Инструмент " + name + ", который имел характеристику: " + instrumentSize);
    }

    public String getName() {
        return name;
    }

    public String getSound() {
        return sound;
    }

    public InstrumentSize getInstrumentSize() {
        return instrumentSize;
    }

    @Override
    public String toString() {
        return "Инструмент " + name;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + instrumentSize.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof MusicalInstrument)) return false;

        MusicalInstrument musicalInstrument = (MusicalInstrument) object;
        return this.name.equals(musicalInstrument.name) && this.sound.equals(musicalInstrument.sound)
                && this.instrumentSize.equals(musicalInstrument.instrumentSize);
    }
}

package Music;

import Music.MusicalInstrument;

public interface Musician {
    void setMusicalInstrument(MusicalInstrument musicalInstrument);
    MusicalInstrument getMusicalInstrument();
    String playOnInstrumentAndGetSound();
    String getName();
}

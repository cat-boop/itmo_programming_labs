package Music;

import Music.MusicalInstrument;

public interface Musician {
    int HEIGHT_FOR_HUGE_INSTRUMENT = 160;

    void setMusicalInstrument(MusicalInstrument musicalInstrument);
    MusicalInstrument getMusicalInstrument();
    String playOnInstrumentAndGetSound();
    String getName();
    int getHeight();
    boolean isStayingOnLadder();
}

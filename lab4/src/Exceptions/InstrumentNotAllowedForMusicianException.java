package Exceptions;

import Music.Musician;

public class InstrumentNotAllowedForMusicianException extends RuntimeException {
    private final int musicianHeight;

    public InstrumentNotAllowedForMusicianException(String musicianName, int musicianHeight) {
        super(musicianName);
        this.musicianHeight = musicianHeight;
    }

    public String getMessage() {
        return "Персонаж " + super.getMessage() + " не может играть на огромном музыкальном инструменте, так как его рост " +
                musicianHeight + " меньше необходимого роста " + Musician.HEIGHT_FOR_HUGE_INSTRUMENT +
                " и Персонаж не стоит на лестнице";
    }
}

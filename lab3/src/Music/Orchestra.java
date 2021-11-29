package Music;

import Things.ThingInterface;

import java.util.*;

public class Orchestra implements ThingInterface {
    private final String name = "Оркестр";
    private final ArrayList<Musician> participants;
    private Set<Musician> uniqueParticipants;
    private final ArrayList<String> melody = new ArrayList<>();

    public Orchestra(Musician... participants) {
        this.participants = new ArrayList<>(Arrays.asList(participants));
        this.uniqueParticipants = new HashSet<>(this.participants);
    }

    public int getNumberOfParticipants() {
        return participants.size();
    }

    public void join() {
        ArrayList<Musician> musicians = new ArrayList<>(uniqueParticipants);
        if (musicians.size() == 1) {
            Musician musician = musicians.get(0);
            MusicalInstrument musicianMusicalInstrument = musician.getMusicalInstrument();
            System.out.println("Все Персонажи оркестра это " + musician + ", которые играют на " +
                    musicianMusicalInstrument + " с характеристикой: " + musicianMusicalInstrument.getInstrumentSize().toString());
        } else {
            System.out.println("Персонажи оркестра это:");
            for (Musician musician : musicians) {
                MusicalInstrument musicianMusicalInstrument = musician.getMusicalInstrument();
                System.out.println("Персонаж " + musician + ", который играет на " + musicianMusicalInstrument
                + " с характеристикой: " + musicianMusicalInstrument.getInstrumentSize().toString());
            }
        }
        System.out.println();
    }

    public void addSoundToMelody(String sound) {
        melody.add(sound);
    }

    public void playMelody() {
        System.out.println("Мелодия начинает плавно литься из музыкальных инструментов:");
        for (String soundOfMelody : melody) System.out.println(soundOfMelody + " ");
    }

    public void addParticipant(Musician musician) {
        participants.add(musician);
        uniqueParticipants.add(musician);
    }

    public void removeParticipant(Musician musician) {
        participants.remove(musician);
        uniqueParticipants = new HashSet<>(participants);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        ArrayList<Musician> musicians = new ArrayList<>(uniqueParticipants);
        String characteristic = getName() + " c " + getNumberOfParticipants() + " Персонажами: ";
        for (Musician musician : musicians) {
            String additional = ", ";
            if (musician.equals(musicians.get(musicians.size() - 1))) additional = ".";
            characteristic += musician.getName() + additional;
        }
        return characteristic;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + participants.hashCode() + melody.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Orchestra)) return false;

        Orchestra orchestra = (Orchestra) object;
        return participants.equals(orchestra.participants) && melody.equals(orchestra.melody);
    }
}

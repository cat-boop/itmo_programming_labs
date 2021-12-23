import Characters.*;
import Enums.*;
import Exceptions.IllegalFloorException;
import Music.InstrumentSize;
import Music.MusicalInstrument;
import Music.Orchestra;
import Things.*;

import java.util.Random;

public class Main {
    private final static int NUMBER_OF_FLOORS = 2;
    private final static int NUMBER_OF_BABIES = 10;
    private final static int FLOOR_WITH_ORCHESTRA = 2;
    private final static boolean MAIN_VISITOR_INVITATION = true;

    public static void main(String[] args) throws IllegalFloorException {
        MusicalInstrument smallHarp = new MusicalInstrument("Маленькая арфа", "туту", InstrumentSize.SMALL);
        MusicalInstrument middleHarp = new MusicalInstrument("Средняя арфа", "тууу", InstrumentSize.MIDDLE);
        MusicalInstrument bigHarp = new MusicalInstrument("Большая арфа", "дутудуу", InstrumentSize.BIG);
        MusicalInstrument hugeHarp = new MusicalInstrument("Огромная арфа", "дудуду", InstrumentSize.HUGE);

        MusicalInstrument[] harps = {smallHarp, middleHarp, bigHarp, hugeHarp};

        Baby[] babies = new Baby[NUMBER_OF_BABIES];
        Random random = new Random();
        for (int i = 0; i < babies.length; i++) {
            babies[i] = new Baby("Малышка " + (i + 1), 160 + random.nextInt(9),
                    harps[random.nextInt(harps.length)]);
        }
        Orchestra orchestra = new Orchestra(babies);

        Alcove.Floor floor = new Alcove.Floor(FLOOR_WITH_ORCHESTRA, orchestra);
        Alcove alcove = new Alcove(NUMBER_OF_FLOORS, Decoration.FLOWER, floor);
        alcove.join();

        System.out.println("Здесь были: ");
        for (MusicalInstrument harp : harps) harp.join();
        System.out.println();

        orchestra.join();

        Citizen citizen = new Citizen("Жители", Location.PLATFORM);
        citizen.comeAndWaitFor(new Visitor("Гости", Location.ZMEEVKA));

        Visitor firstVisitor = new Visitor("Гвоздик", Location.ZMEEVKA, true);
        Visitor.Outlook firstVisitorOutlook = firstVisitor.new Outlook(new Shirt(true), Appearance.WASHED, Appearance.COMBED);
        firstVisitor.setOutlook(firstVisitorOutlook);

        Visitor secondVisitor = new Visitor("Шурупчик", Location.ZMEEVKA, MAIN_VISITOR_INVITATION, firstVisitor);
        Visitor thirdVisitor = new Visitor("Бублик", Location.ZMEEVKA, MAIN_VISITOR_INVITATION, firstVisitor);

        Visitor[] visitors = {firstVisitor, secondVisitor, thirdVisitor};
        for (Visitor visitor : visitors) {
            visitor.appear();
            visitor.analyzeOutlook();
            visitor.comeTo(Location.PROM);
        }

        Visitor otherVisitors = new Visitor("Остальные жители", Location.ZMEEVKA, false, secondVisitor, thirdVisitor);
        otherVisitors.appear();
        boolean resultOfAttempt = otherVisitors.comeTo(Location.PROM);
        if (!resultOfAttempt) {
            while (!otherVisitors.isInvited()) otherVisitors.sayThanksTo(Location.PROM, babies);
            otherVisitors.comeTo(Location.PROM);
        }

        class Dunno extends Human {
            private Location location;
            private Action action;
            private boolean isSitting, isResting, isSleeping;

            public Dunno(String name, Location location) {
                super(name);
                this.location = location;
                System.out.println("Персонаж " + getName() + " находится по местоположению " + location);
            }

            public void analyzeAction() {
                Action firstActionNotDoing, secondActionNotDoing, actionDoing;
                if (isSitting) {
                    actionDoing = Action.SITTING;
                    firstActionNotDoing = Action.RESTING;
                    secondActionNotDoing = Action.SLEEPING;
                } else if (isResting) {
                    actionDoing = Action.RESTING;
                    firstActionNotDoing = Action.SITTING;
                    secondActionNotDoing = Action.SLEEPING;
                } else {
                    actionDoing = Action.SLEEPING;
                    firstActionNotDoing = Action.RESTING;
                    secondActionNotDoing = Action.SITTING;
                }
                System.out.println("Персонаж " + getName() + " не то чтобы делал " + firstActionNotDoing + " и "
                        + secondActionNotDoing + ", сколько делал " + actionDoing);
            }

            public void appear() {
                if (Baby.isMeeting) {
                    location = Location.PLATFORM;
                    System.out.println("Персонаж " + getName() + " закончил " + action +
                            " и пошел к Местопожению \"" + location + "\"");
                } else {
                    System.out.println("Персонаж " + getName() + "продолжил " + action + "");
                }
            }

            public void setSitting() {
                action = Action.SITTING;
                isSitting = true;
                isResting = false;
                isSleeping = false;
            }

            public void setResting() {
                action = Action.RESTING;
                isResting = true;
                isSitting = false;
                isSleeping = false;
            }

            public void setSleeping() {
                action = Action.SLEEPING;
                isSleeping = true;
                isSitting = false;
                isResting = false;
            }
        }

        Dunno dunno = new Dunno("Незнайка", Location.DANDELION);
        dunno.setSleeping();

        dunno.analyzeAction();
        Baby.startMeeting();
        dunno.appear();
    }
}

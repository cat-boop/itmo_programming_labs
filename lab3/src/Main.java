import Characters.*;
import Enums.*;
import Music.InstrumentSize;
import Music.MusicalInstrument;
import Music.Orchestra;
import Things.*;

public class Main {
    private final static int NUMBER_OF_FLOORS = 2;
    private final static int NUMBER_OF_BABIES = 10;
    private final static int FLOOR = 2;
    private final static String ZMEEVKA_CITY = "Змеевка";
    private final static boolean MAIN_VISITOR_INVITATION = true;

    public static void main(String[] args) {
        MusicalInstrument smallHarp = new MusicalInstrument("Маленькая арфа", "туту", InstrumentSize.SMALL);
        MusicalInstrument middleHarp = new MusicalInstrument("Средняя арфа", "тууу", InstrumentSize.MIDDLE);
        MusicalInstrument bigHarp = new MusicalInstrument("Большая арфа", "дутудуу", InstrumentSize.BIG);
        MusicalInstrument hugeHarp = new MusicalInstrument("Огромная арфа", "дудуду", InstrumentSize.HUGE);

        MusicalInstrument[] harps = {smallHarp, middleHarp, bigHarp, hugeHarp};

        MusicalInstrument harpForBaby = new MusicalInstrument("Небольшая арфа", "уу", InstrumentSize.SMALL);
        Baby[] babies = new Baby[NUMBER_OF_BABIES];
        for (int i = 0; i < babies.length; i++) {
            babies[i] = new Baby("Малышка", 155, harpForBaby);
        }
        Orchestra orchestra = new Orchestra(babies);

        Floor floor = new Floor(FLOOR, orchestra);
        Alcove alcove = new Alcove(NUMBER_OF_FLOORS, Decoration.FLOWER, floor);
        alcove.join();

        System.out.println("Здесь были: ");
        for (MusicalInstrument harp : harps) harp.join();
        System.out.println();

        orchestra.join();

        Citizen citizen = new Citizen("Жители");
        citizen.comeAndWaitFor(new Visitor("Гости", Location.ZMEEVKA));

        Visitor firstVisitor = new Visitor("Гвоздик", Location.ZMEEVKA, MAIN_VISITOR_INVITATION);
        firstVisitor.setAppearance(new Shirt(true), Appearance.WASHED, Appearance.COMBED);

        Visitor secondVisitor = new Visitor("Шурупчик", Location.ZMEEVKA, MAIN_VISITOR_INVITATION, firstVisitor);
        Visitor thirdVisitor = new Visitor("Бублик", Location.ZMEEVKA, MAIN_VISITOR_INVITATION, firstVisitor);

        Visitor[] visitors = {firstVisitor, secondVisitor, thirdVisitor};
        for (Visitor visitor : visitors) {
            visitor.appear();
            visitor.analyzeAppearance();
            visitor.comeTo(Location.PROM);
        }

        Visitor otherVisitors = new Visitor("Остальные жители", Location.ZMEEVKA, false, secondVisitor, thirdVisitor);
        otherVisitors.appear();
        boolean resultOfAttempt = otherVisitors.comeTo(Location.PROM);
        if (!resultOfAttempt) {
            while (!otherVisitors.isInvited()) otherVisitors.sayThanksTo(Location.PROM, babies);
            otherVisitors.comeTo(Location.PROM);
        }
    }
}

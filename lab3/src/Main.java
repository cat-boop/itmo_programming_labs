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
        citizen.comeAndWaitFor(new Visitor("Гости", ZMEEVKA_CITY));

        MainVisitor firstVisitor = new MainVisitor("Гвоздик", ZMEEVKA_CITY, MAIN_VISITOR_INVITATION,
                new Shirt(true), Appearance.COMBED, Appearance.WASHED);
        MainVisitor secondVisitor = new MainVisitor("Шурупчик", ZMEEVKA_CITY, MAIN_VISITOR_INVITATION,
                firstVisitor);
        MainVisitor thirdVisitor = new MainVisitor("Бублик", ZMEEVKA_CITY, MAIN_VISITOR_INVITATION,
                firstVisitor);

        MainVisitor[] mainVisitors = {firstVisitor, secondVisitor, thirdVisitor};
        for (MainVisitor mainVisitor : mainVisitors) {
            mainVisitor.appear();
            mainVisitor.analyzeAppearance();
            mainVisitor.comeToProm();
        }

        Visitor otherVisitors = new Visitor("Остальные жители", ZMEEVKA_CITY, false, secondVisitor, thirdVisitor);
        otherVisitors.appear();
        boolean resultOfAttempt = otherVisitors.comeToProm();
        if (!resultOfAttempt) {
            while (!otherVisitors.isInvited()) otherVisitors.sayThanksTo(babies);
            otherVisitors.comeToProm();
        }
    }
}

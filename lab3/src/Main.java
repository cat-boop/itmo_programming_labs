import Enums.Appearance;
import Enums.Decoration;
import Enums.HarpSize;

public class Main {
    private final static int NUMBER_OF_FLOORS = 2;
    private final static int FLOOR = 2;
    private final static int CAPACITY_ON_FLOOR = 10;
    private final static String ZMEEVKA_CITY = "Змеевка";

    public static void main(String[] args) {
        //На втором этаже беседки, которая была украшена цветами, помещался оркестр из десяти малышек.
        CapacityOnFloor capacityOnFloor = new CapacityOnFloor(FLOOR, CAPACITY_ON_FLOOR, new Baby());
        Alcove alcove = new Alcove(NUMBER_OF_FLOORS, capacityOnFloor, Decoration.FLOWER);
        alcove.join();

        Baby baby = new Baby();
        Harp harp = new Harp(HarpSize.TooSmall, HarpSize.Middle, HarpSize.Huge, HarpSize.TooBig);
        baby.playOn(harp);
        harp.join();

        Citizen citizen = new Citizen("Жители", new Visitor("Гости", ZMEEVKA_CITY));
        citizen.come();

        Visitor firstVisitor = new Visitor("Гвоздик", ZMEEVKA_CITY, Appearance.CLEAN_SHIRT, Appearance.WASHED, Appearance.COMBED_WITH_SMALL_ISSUE);
        Visitor secondVisitor = new Visitor("Шурупчик", ZMEEVKA_CITY, firstVisitor, Appearance.DEFAULT);
        Visitor thirdVisitor = new Visitor("Бублик", ZMEEVKA_CITY, firstVisitor, Appearance.DEFAULT);

        Visitor[] visitors = {firstVisitor, secondVisitor, thirdVisitor};
        for (Visitor visitor : visitors) {
            visitor.come();
            visitor.analyzeAppearance();
        }

        OtherVisitor otherVisitors = new OtherVisitor("Остальные жители", ZMEEVKA_CITY, false, secondVisitor, thirdVisitor);
        otherVisitors.come();
        otherVisitors.giveThanks();
        otherVisitors.getInvite();
    }
}

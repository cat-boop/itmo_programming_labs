package Characters;

public abstract class AbstractHuman implements HumanInterface {
    private final String abstractName;

    protected AbstractHuman(String name) {
        this.abstractName = name;
    }

    @Override
    public String getName() {
        return abstractName;
    }
}

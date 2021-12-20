package Things;

public class Shirt implements ThingInterface {
    private final String name = "Рубашка";
    private boolean cleaned;

    public Shirt(boolean cleaned) {
        this.cleaned = cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public String getCharacteristic() {
        return getName() + ", которая выглядит " + (cleaned ? "чистой" : "грязной");
    }

    @Override
    public String getName() {
        return "Объект " + name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(cleaned);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Shirt)) return false;

        Shirt shirt = (Shirt) object;
        return cleaned == shirt.cleaned;
    }
}

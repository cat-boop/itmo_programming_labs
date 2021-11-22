import Enums.HarpSize;
import Interfaces.ThingInterface;

import java.util.Arrays;

public class Harp implements ThingInterface {
    private final String name = "\"Арфа\"";
    private final HarpSize[] harpSizes;

    public Harp(HarpSize... harpSizes) {
        this.harpSizes = harpSizes;
    }

    @Override
    public String getName() {
        return name;
    }

    public void join() {
        System.out.println("Здесь были:");
        for (HarpSize harpSize : harpSizes) System.out.println(harpSize.getCharacteristic());
        System.out.println();
    }

    @Override
    public String toString() {
        return "Объект " + name;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + Arrays.hashCode(harpSizes);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Harp)) return false;

        Harp harp = (Harp) object;
        return Arrays.equals(harpSizes, harp.harpSizes);
    }
}

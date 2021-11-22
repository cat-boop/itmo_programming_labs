package Characters;

import Things.ThingInterface;

public interface HumanInterface extends ThingInterface {
    String toString();

    int hashCode();

    boolean equals(Object object);
}

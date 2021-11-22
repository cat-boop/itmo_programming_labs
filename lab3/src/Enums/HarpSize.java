package Enums;

public enum HarpSize {
    TooSmall("Совсем маленькая арфа, которую надо держать на руках"),
    Middle("Арфа побольше, которую держат на коленях"),
    TooBig("Большая арфа, которая стоит на полу"),
    Huge("Совсем огромная арфа: чтобы играть на ней, нужно взбираться на лесенку");

    private final String characteristic;
    HarpSize(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getCharacteristic() {
        return characteristic;
    }
}

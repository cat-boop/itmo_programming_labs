package Music;

public enum InstrumentSize {
    SMALL("Совсем маленький инструмент, который надо держать на руках"),
    MIDDLE("Инструмент побольше, который держат на коленях"),
    BIG("Большой инструмент, который стоит на полу"),
    HUGE("Совсем огромный инструмент: чтобы играть на нём, нужно взбираться на лесенку");

    private final String characteristic;

    InstrumentSize(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getCharacteristic() {
        return characteristic;
    }
}

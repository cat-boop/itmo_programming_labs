package Enums;

public enum Appearance {
    WASHED("Умытый"),
    COMBED("Причесанный");

    private final String appearance;

    Appearance(String appearance) {
        this.appearance = appearance;
    }

    public String getCharacteristic() {
        return appearance;
    }
}

package Enums;

public enum Appearance {
    CLEAN_SHIRT("Чистая рубашка"),
    WASHED("Умытый"),
    COMBED_WITH_SMALL_ISSUE("Причесанный, однако один вихор на макушке все-таки торчит"),
    DEFAULT("");

    private String appearance;

    Appearance(String appearance) {
        this.appearance = appearance;
    }

    public String getAppearance() {
        return appearance;
    }
}

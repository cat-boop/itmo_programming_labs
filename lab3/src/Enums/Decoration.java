package Enums;

public enum Decoration {
    FLOWER {
        public String getName() {
            return "Цветок";
        }
    },
    DEFAULT {
        public String getName() {
            return null;
        }
    };

    public abstract String getName();
}

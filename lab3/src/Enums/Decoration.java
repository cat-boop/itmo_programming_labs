package Enums;

public enum Decoration {
    FLOWER {
        public String toString() {
            return "Цветок";
        }
    },
    DEFAULT {
        public String toString() {
            return null;
        }
    };

    public abstract String toString();
}

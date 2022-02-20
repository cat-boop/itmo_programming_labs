package com.lab.client;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try {
            Console console = new Console(args[0]);
            console.startInteractiveMode();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Имя файла не указано");
        }

    }
}

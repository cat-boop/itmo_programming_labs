package com.lab.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public final class Deserializer {

    private Deserializer() {
    }

    public static Response deserializeResponse(byte[] serializedObject) {
        try (ByteArrayInputStream arrayOutputStream = new ByteArrayInputStream(serializedObject);
             ObjectInputStream objectInputStream = new ObjectInputStream(arrayOutputStream)) {
            return (Response) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //TODO make own exception
            throw new RuntimeException("Cannot deserialize");
        }
    }
}

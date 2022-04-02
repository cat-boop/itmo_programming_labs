package com.lab.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public final class Serializer {

    private Serializer() {
    }

    public static ByteBuffer serializeRequest(Request request) {
        try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(arrayOutputStream)) {
            outputStream.writeObject(request);
            outputStream.flush();
            byte[] serializedObject = arrayOutputStream.toByteArray();
            return ByteBuffer.wrap(serializedObject);
        } catch (IOException e) {
            //TODO made own exception
            throw new RuntimeException("Can't serialize");
        }
    }
}

package com.lab.common.util;

import com.lab.common.Exceptions.SerializeException;

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
            return ByteBuffer.wrap(arrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new SerializeException("Невозможно сериализовать запрос");
        }
    }

    public static byte[] serializeResponse(Response response) {
        try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(arrayOutputStream)) {
            outputStream.writeObject(response);
            outputStream.flush();
            return arrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SerializeException("Невозможно сериализовать запрос");
        }
    }
}

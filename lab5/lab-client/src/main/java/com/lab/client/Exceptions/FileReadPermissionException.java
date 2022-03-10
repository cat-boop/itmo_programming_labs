package com.lab.client.Exceptions;

public class FileReadPermissionException extends RuntimeException {
    public FileReadPermissionException(String message) {
        super(message);
    }
}

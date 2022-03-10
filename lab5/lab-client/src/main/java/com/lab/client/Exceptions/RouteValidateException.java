package com.lab.client.Exceptions;

public class RouteValidateException extends RuntimeException {
    public RouteValidateException() {
        this("Ошибка при валидации пути");
    }

    public RouteValidateException(String message) {
        super(message);
    }
}

package com.lab.common.Exceptions;

/**
 * exception class shows that script contains error
 */
public class ReadElementFromScriptException extends RuntimeException {
    public ReadElementFromScriptException(String message, Throwable cause) {
        super(message, cause);
    }
}

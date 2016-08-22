package com.rockit.core.exception;

public class ApiException extends RuntimeException {
    private int errorCode;

    public ApiException(int errorCode) {
        this(errorCode, null, null);
    }

    public ApiException(int errorCode, String message) {
        this(errorCode, message, null);
    }

    public ApiException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ApiException(int errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
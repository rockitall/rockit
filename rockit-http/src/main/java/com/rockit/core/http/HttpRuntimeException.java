package com.rockit.core.http;

/**
 * Created by Allen on 2016/7/31.
 */
public class HttpRuntimeException extends RuntimeException {
    public HttpRuntimeException() {
        super();
    }

    public HttpRuntimeException(String message) {
        super(message);
    }

    public HttpRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRuntimeException(Throwable cause) {
        super(cause);
    }

    protected HttpRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

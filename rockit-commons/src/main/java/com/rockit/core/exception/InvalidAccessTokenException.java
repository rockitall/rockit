package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by allen on 16-2-5.
 */
public class InvalidAccessTokenException extends ApiException {
    public InvalidAccessTokenException() {
        super(ErrorCode.INVALID_ACCESS_TOKEN);
    }

    public InvalidAccessTokenException(String message, Throwable cause) {
        super(ErrorCode.INVALID_ACCESS_TOKEN, cause);
    }
}

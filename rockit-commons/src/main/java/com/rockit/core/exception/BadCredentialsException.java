package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by allen on 16-2-5.
 */
public class BadCredentialsException extends ApiException {
    public BadCredentialsException() {
        super(ErrorCode.BAD_CREDENTIALS);
    }
}
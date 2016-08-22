package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by allen on 16-2-5.
 */
public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}

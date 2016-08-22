package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by allen on 16-2-5.
 */
public class UserNotExistsException extends ApiException {
    public UserNotExistsException() {
        super(ErrorCode.USER_NOT_EXISTS);
    }
}

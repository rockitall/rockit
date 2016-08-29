package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by Allen on 2016/8/25.
 */
public class PasswordModificationException extends ApiException {
    public PasswordModificationException() {
        super(ErrorCode.INCORRECT_OLD_PASSWORD);
    }
}

package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by allen on 16-2-5.
 */
public class VerifyCodeIncorrectException extends ApiException {
    public VerifyCodeIncorrectException() {
        super(ErrorCode.SMS_VERIFY_CODE_INCORRECT);
    }
}


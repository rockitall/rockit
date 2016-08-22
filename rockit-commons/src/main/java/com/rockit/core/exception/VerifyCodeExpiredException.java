package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by Allen on 2016/7/30.
 */
public class VerifyCodeExpiredException extends ApiException {
    public VerifyCodeExpiredException() {
        super(ErrorCode.SMS_VERIFY_CODE_EXPIRED);
    }
}

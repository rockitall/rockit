package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by Allen on 2016/7/30.
 */
public class IllegalApiParameterException extends ApiException {
    public IllegalApiParameterException(String message) {
        super(ErrorCode.ILLEGAL_PARAMETER, message);
    }
}

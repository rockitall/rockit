package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by allen on 16-2-5.
 */
public class UnauthorizedException extends ApiException {
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}

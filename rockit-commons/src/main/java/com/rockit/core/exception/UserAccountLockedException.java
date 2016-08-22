package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by allen on 16-2-5.
 */
public class UserAccountLockedException extends ApiException {
    public UserAccountLockedException() {
        super(ErrorCode.ACCOUNT_LOCKED);
    }
}

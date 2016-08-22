package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * Created by allen on 16-2-5.
 */
public class OperationDeniedException extends ApiException {
    public OperationDeniedException() {
        super(ErrorCode.USER_OPERATION_DENY);
    }
}

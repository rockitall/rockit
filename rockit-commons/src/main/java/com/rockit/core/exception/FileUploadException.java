/**
 * zhandc 2016年8月27日
 */
package com.rockit.core.exception;

import com.rockit.core.code.ErrorCode;

/**
 * @author zhandc 2016年8月27日
 */
public class FileUploadException extends ApiException {
    public FileUploadException() {
        super(ErrorCode.FILE_UPLOAD_ERROR);
    }
}

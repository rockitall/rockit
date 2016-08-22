package com.rockit.qcloud.im.common;

/**
 * Created by Allen on 2016/8/13.
 */
public class QCloudApiResponse {
    private String ActionStatus;
    private String ErrorInfo;
    private int ErrorCode;

    public String getActionStatus() {
        return ActionStatus;
    }

    public void setActionStatus(String actionStatus) {
        ActionStatus = actionStatus;
    }

    public String getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        ErrorInfo = errorInfo;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }
}

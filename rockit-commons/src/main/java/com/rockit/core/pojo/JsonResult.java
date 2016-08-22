package com.rockit.core.pojo;

import com.rockit.core.code.ErrorCode;

public class JsonResult {
    private int code;
    private String msg;
    private Object data;

    private JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(ErrorCode.SUCCESS, "OK", data);
    }

    public static JsonResult ok() {
        return ok(null);
    }

    public static JsonResult error(int code, String msg) {
        return new JsonResult(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

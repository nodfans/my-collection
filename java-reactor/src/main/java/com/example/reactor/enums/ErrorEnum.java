package com.example.reactor.enums;

public enum ErrorEnum {
    SUCCESS(200, "SUCCESS"),
    PARAMS_ERROR(400, "PARAMS_ERROR"),
    METHOD_FAIL(500, "ENCOUNTER AN ERROR WHEN EXECUTE METHOD"),
    UNKNOWN_EXCEPTION(500, "THIS IS AN UNKNOWN EXCEPTION");


    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
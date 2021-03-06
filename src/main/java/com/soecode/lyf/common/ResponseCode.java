package com.soecode.lyf.common;

public enum ResponseCode {
    /**
     * 枚举类
     */
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENET");

    private final int code ;
    private final String desc ;
    ResponseCode(int code , String desc) {
        this.code = code ;
        this.desc = desc ;
    }

    public int getCode() { return code; }
    public String getDesc() {
        return desc;
    }

}

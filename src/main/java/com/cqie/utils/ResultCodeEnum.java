package com.cqie.utils;

public enum ResultCodeEnum {
    SUCCESS(100200, "返回成功"),
    SYSTEM_EXCEPTION(100500, "系统异常"),
    REQUEST_PARAM_ERROR(100401, "请求参数检验错误"),
    REQUEST_OUT_OVERTIME(100408, "请求超时"),
    REQUEST_NOT_FOUND(100404, "请求的资源或服务未找到"),
    REQUEST_LENGTH_LIMIT(100414, "请求URI太长"),
    REQUEST_Format_NOT_SUPPORTED(100415, "请求的格式不支持"),
    ;
    /**
     * 枚举值
     */
    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}

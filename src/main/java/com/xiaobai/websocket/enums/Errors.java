package com.xiaobai.websocket.enums;

/**
 * @author dingfeng.xiao
 */
public enum Errors {
    SYS_UNKNOWN_ERROR("-1", "系统未知错误"),
    BODY_BLANK_ERROR("015","body为空"),
    NOT_FOUND_ERRROR("404", "未知路径")
    ;

    private final String errorCode;
    private final String errorMsg;

    /**
     * @param errorCode
     * @param errorMsg
     */
    Errors(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

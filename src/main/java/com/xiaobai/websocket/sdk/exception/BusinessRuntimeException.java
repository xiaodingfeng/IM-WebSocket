package com.xiaobai.websocket.sdk.exception;

import com.xiaobai.websocket.enums.Errors;

/**
 * @author dingfeng.xiao
 */
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;

    private final String errorMsg;

    public BusinessRuntimeException(String errorCode, String errorMsg) {
        super(errorCode + "");
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessRuntimeException(Errors errorCode) {
        this(errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "BusinessRuntimeException occurred, errorCode=" + errorCode + ",errorMsg=" + errorMsg;
    }

}

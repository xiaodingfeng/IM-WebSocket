package com.xiaobai.websocket.sdk.convert;


import com.xiaobai.websocket.enums.Errors;

import java.io.Serializable;

/**
 * @author dingfeng.xiao
 */
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private String ok;

    private T data;


    public ApiResponse() {
    }


    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode("200");
        apiResponse.setMsg("");
        apiResponse.setOk("true");
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> fail(Errors errors) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errors.getErrorCode());
        apiResponse.setMsg(errors.getErrorMsg());
        apiResponse.setOk("false");
        return apiResponse;
    }

    public static <T> ApiResponse<T> fail(String code, String msg) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(code);
        apiResponse.setMsg(msg);
        apiResponse.setOk("false");
        return apiResponse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

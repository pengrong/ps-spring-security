package com.inpsur.gcloud.ps.core.support;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private T data;

    private AuthenticationResponse<T> code(String code) {
        this.code = code;
        return this;
    }

    private AuthenticationResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    private AuthenticationResponse<T> data(T data) {
        this.data = data;
        return this;
    }

    public static <T> AuthenticationResponse<T> success(T data) {
        AuthenticationResponse<T> resp = new AuthenticationResponse<T>();
        return resp.code(ResponseCodeEnum.SUCCESS.getCode()).message(ResponseCodeEnum.SUCCESS.getDesc()).data(data);
    }

    public static <T> AuthenticationResponse<T> failed(ResponseCodeEnum errorCodeEnum) {
        AuthenticationResponse<T> resp = new AuthenticationResponse<T>();
        return resp.code(errorCodeEnum.getCode()).message(errorCodeEnum.getDesc());
    }
    public static <T> AuthenticationResponse<T> failed(String code, String message) {
        AuthenticationResponse<T> resp = new AuthenticationResponse<T>();
        return resp.code(code).message(message);
    }

}

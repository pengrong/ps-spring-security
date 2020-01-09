package com.inpsur.gcloud.ps.core.support;

public enum ResponseCodeEnum {
    SUCCESS("0", "认证成功"),
    UNAUTHORIZED_ERROR("0001", "需要用户认证，请引导至登录页"),
    COMMON_ACCOUNT_ERROR("1001", "账号错误"),
    SYSTEM_UNKNOWN_ERROR("-1", "系统繁忙，请稍后再试....");
    private String code;
    private String desc;

    ResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }
}

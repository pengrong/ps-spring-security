package com.inpsur.gcloud.ps.core.properties;

public class BrowserProperties {
    // 默认登录页面,保存在classes/resources下
    private String loginPage = "/signin.html";
    private com.inpsur.gcloud.ps.core.properties.LoginType loginType = com.inpsur.gcloud.ps.core.properties.LoginType.JSON;

    public com.inpsur.gcloud.ps.core.properties.LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(com.inpsur.gcloud.ps.core.properties.LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

}

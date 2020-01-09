package com.inpsur.gcloud.ps.core.userdetails;

import lombok.Data;

@Data
public class PsUserInfo {

    //    loginname	用户登录名
    private String loginname;
    //    name	用户姓名（已实名的用户，姓名才具有真实性）
    private String name;
    //    code	用户编码
    private String code;
    //    paperstype	证件类型（默认为1） 1身份证 3港澳通行证 4台湾通行证 5外籍人士永久居留证
    private String paperstype;
    //    papersnumber	证件号码
    private String papersnumber;
    //    mobile	手机号码
    private String mobile;
    //    email	电子邮箱
    private String email;
    //    isauth	是否实名：1-已实名；0-未实名
    private String isauth;
    //    authlevel	实名等级：0:-未认证，1-初级；2-中级；3-高级；
    private String authlevel;
    //    nation	民族，见国标GB3304-91
    private String nation;
    //    regtime	注册时间
    private String regtime;
    //    logintime	上一次登录时间
    private String logintime;
    //    sex	性别: 1-男； 0-女
    private String sex;
    //    post	邮编
    private String post;
    //    address	地址
    private String address;
}

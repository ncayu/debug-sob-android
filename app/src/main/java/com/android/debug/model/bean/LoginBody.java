package com.android.debug.model.bean;

/**
 * @author: 123
 * @date: 2021/2/1
 * @description $
 */
public class LoginBody {
    private String password;
    private String phoneNum;

    public LoginBody(String phoneNum, String password) {
        this.password = password;
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
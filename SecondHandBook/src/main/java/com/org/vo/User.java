package com.org.vo;

public class User {
    private int uId; //用户id
    private String uName; //用户姓名
    private String pswd; //用户登录密码
    private String pNumber; //用户电话号码

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", pswd='" + pswd + '\'' +
                ", pNumber='" + pNumber + '\'' +
                '}';
    }
}

package com.nokia.tudms.beans;

/**
 * @Author Rin
 * @Date 2017/4/24
 */
public class LoggedUser {
    private int userId;
    private String userName;
    private String userToken;
    private int userLevel;
    public LoggedUser(){}

    public LoggedUser(int userId, String userName, String userToken, int userLevel) {
        this.userId = userId;
        this.userName = userName;
        this.userToken = userToken;
        this.userLevel = userLevel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}

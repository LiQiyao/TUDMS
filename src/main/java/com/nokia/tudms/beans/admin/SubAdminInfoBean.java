package com.nokia.tudms.beans.admin;

import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/5/2
 */
public class SubAdminInfoBean {
    int uid;
    String userName;
    ArrayList<Integer> authCategory;

    public SubAdminInfoBean() {
        authCategory = new ArrayList<Integer>();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Integer> getAuthCategory() {
        return authCategory;
    }

    public void setAuthCategory(ArrayList<Integer> authCategory) {
        this.authCategory = authCategory;
    }

    public void appendAuthCategory(int categoryId){
        authCategory.add(categoryId);
    }
}

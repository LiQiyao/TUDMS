package com.nokia.tudms.beans.usercenter;

import java.util.ArrayList;

/**
 * Created by Lee on 2017/4/26.
 * 个人中心 用户上传的工具列表
 */
public class UserCenterToolList {

    private int num = 0;
    private ArrayList<UserCenterTool> list = new ArrayList<UserCenterTool>();

    public void addUserCenterTool(UserCenterTool userCenterTool){
        list.add(userCenterTool);
        this.num++;
    }

    public void removeUserCenterTool(UserCenterTool userCenterTool){
        list.remove(userCenterTool);
    }

    public void removeUserCenterTool(int index){
        list.remove(index);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<UserCenterTool> getList() {
        return list;
    }

    public void setList(ArrayList<UserCenterTool> list) {
        this.list = list;
    }
}

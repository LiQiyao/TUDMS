package com.nokia.tudms.beans.usercenter;

import java.util.ArrayList;

/**
 * Created by Lee on 2017/4/25.
 * 消息推送的评论列表
 */
public class UserCenterMessageList {

    private int num = 0;
    private int unRead = 0;
    private ArrayList<UserCenterMessage> list = new ArrayList<UserCenterMessage>();

    public void addMessage(UserCenterMessage userCenterMessage){
        list.add(userCenterMessage);
        this.num++;
    }

    public int getUnRead() {
        return unRead;
    }

    public void setUnRead(int unRead) {
        this.unRead = unRead;
    }

    public void removeMessage(int index){
        list.remove(index);
    }

    public void removeMessage(UserCenterMessage userCenterMessage){
        list.remove(userCenterMessage);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<UserCenterMessage> getList() {
        return list;
    }

    public void setList(ArrayList<UserCenterMessage> list) {
        this.list = list;
    }
}

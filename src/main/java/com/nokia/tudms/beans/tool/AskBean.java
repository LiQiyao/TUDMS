package com.nokia.tudms.beans.tool;

import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/4/30
 */
public class AskBean {
    private int askId;
    private int senderUid;
    private String senderName;
    private int toolId;
    private int time;
    private String content;
    private ArrayList<ReplyBean> replyList;

    public AskBean(){}

    public AskBean(int askId, int senderUid, String senderName, int toolId, int time, String content) {
        this.askId = askId;
        this.senderUid = senderUid;
        this.senderName = senderName;
        this.toolId = toolId;
        this.time = time;
        this.content = content;
    }

    public int getAskId() {
        return askId;
    }

    public void setAskId(int askId) {
        this.askId = askId;
    }

    public int getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(int senderUid) {
        this.senderUid = senderUid;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<ReplyBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(ArrayList<ReplyBean> replyList) {
        this.replyList = replyList;
    }
}

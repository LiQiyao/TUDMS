package com.nokia.tudms.beans.tool;

/**
 * @Author Rin
 * @Date 2017/4/30
 */
public class ReplyBean {
    private int replyId;
    private int replySenderUid;
    private String replySenderName;
    private int askId;
    private int replyTargetUid;
    private String replyTargetName;
    private int replyTime;
    private String replyContent;

    public ReplyBean(){}

    public ReplyBean(int replyId, int replySenderUid, String replySenderName, int askId, int replyTargetUid, String replyTargetName, int replyTime, String replyContent) {
        this.replyId = replyId;
        this.replySenderUid = replySenderUid;
        this.replySenderName = replySenderName;
        this.askId = askId;
        this.replyTargetUid = replyTargetUid;
        this.replyTargetName = replyTargetName;
        this.replyTime = replyTime;
        this.replyContent = replyContent;
    }

    public ReplyBean(int replyId, int replySenderUid, String replySenderName, int askId, int replyTargetUid, String replyTargetName, int replyTime) {
        this.replyId = replyId;
        this.replySenderUid = replySenderUid;
        this.replySenderName = replySenderName;
        this.askId = askId;
        this.replyTargetUid = replyTargetUid;
        this.replyTargetName = replyTargetName;
        this.replyTime = replyTime;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getReplySenderUid() {
        return replySenderUid;
    }

    public void setReplySenderUid(int replySenderUid) {
        this.replySenderUid = replySenderUid;
    }

    public String getReplySenderName() {
        return replySenderName;
    }

    public void setReplySenderName(String replySenderName) {
        this.replySenderName = replySenderName;
    }

    public int getAskId() {
        return askId;
    }

    public void setAskId(int askId) {
        this.askId = askId;
    }

    public int getReplyTargetUid() {
        return replyTargetUid;
    }

    public void setReplyTargetUid(int replyTargetUid) {
        this.replyTargetUid = replyTargetUid;
    }

    public String getReplyTargetName() {
        return replyTargetName;
    }

    public void setReplyTargetName(String replyTargetName) {
        this.replyTargetName = replyTargetName;
    }

    public int getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(int replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}

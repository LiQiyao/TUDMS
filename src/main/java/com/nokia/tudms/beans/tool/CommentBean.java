package com.nokia.tudms.beans.tool;

/**
 * @Author Rin
 * @Date 2017/4/29
 */
public class CommentBean {
    private int commentId;
    private int senderUid;
    private String senderName;
    private String content;
    private int commentTime;

    public CommentBean(){}
    public CommentBean(int commentId, int senderUid, String senderName, String content, int commentTime) {
        this.commentId = commentId;
        this.senderUid = senderUid;
        this.senderName = senderName;
        this.content = content;
        this.commentTime = commentTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(int commentTime) {
        this.commentTime = commentTime;
    }
}

package com.nokia.tudms.beans.usercenter;

/**
 * Created by Lee on 2017/4/25.
 * 消息推送中的评论
 */
public class UserCenterMessage {
    private int messageId;
    private int receiverId;
    private int type;
    private String title;
    private String content;
    private String sendTime;
    private String link;
    private int haveRead;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getHaveRead() {
        return haveRead;
    }

    public void setHaveRead(int haveRead) {
        this.haveRead = haveRead;
    }
}

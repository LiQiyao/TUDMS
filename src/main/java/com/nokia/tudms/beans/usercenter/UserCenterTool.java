package com.nokia.tudms.beans.usercenter;

/**
 * Created by Lee on 2017/4/26.
 * 个人中心 用户已经上传的工具
 */
public class UserCenterTool {

    private int toolId;
    private String name;
    private int uploaderUId;
    private int category;
    private String briefIntro;
    private String description;
    private int downloadCount;
    private int zanCount;
    private int commentCount;
    private String newestVersionTime;

    public int getZanCount() {
        return zanCount;
    }

    public void setZanCount(int zanCount) {
        this.zanCount = zanCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getNewestVersionTime() {
        return newestVersionTime;
    }

    public void setNewestVersionTime(String newestVersionTime) {
        this.newestVersionTime = newestVersionTime;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUploaderUId() {
        return uploaderUId;
    }

    public void setUploaderUId(int uploaderUId) {
        this.uploaderUId = uploaderUId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}

package com.nokia.tudms.beans.tool;

import java.util.ArrayList;

/**
 * 供工具详情页使用
 * @Author Rin
 * @Date 2017/4/30
 */
public class ToolDetailBean {
    private int toolId;
    private String toolName;
    private int categoryId;
    private String categoryName; //join
    private int uploaderUid;
    private String uploaderName;
    private int zanCount;
    private int commentCount;
    private int downloadCount;
    private String briefIntro;
    private String description;
    private ArrayList<String> tagList; //
    private ArrayList<VersionBean> versionList; //

    public ToolDetailBean() {
    }

    public int getUploaderUid() {
        return uploaderUid;
    }

    public void setUploaderUid(int uploaderUid) {
        this.uploaderUid = uploaderUid;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
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

    public ArrayList<String> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<String> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<VersionBean> getVersionList() {
        return versionList;
    }

    public void setVersionList(ArrayList<VersionBean> versionList) {
        this.versionList = versionList;
    }
}

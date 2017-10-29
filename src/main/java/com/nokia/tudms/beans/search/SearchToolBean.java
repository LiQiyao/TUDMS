package com.nokia.tudms.beans.search;

import java.util.ArrayList;

/**
 * Created by Lee on 2017/4/27.
 * 搜索页面中搜索得到的缩略的工具Bean
 */
public class SearchToolBean {
    private int toolId;
    private String toolName;
    private int uploaderUId;
    private String uploaderName;
    private String brefIntro;
    private int downloadCount;
    private int zanCount;
    private int commentCount;
    private float fileSize;
    private int uploadTime;
    private String nowVersion;
    private ArrayList<String> tagList = new ArrayList<String>();
    private ArrayList<String> versionList = new ArrayList<String>();

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

    public int getUploaderUId() {
        return uploaderUId;
    }

    public void setUploaderUId(int uploaderUId) {
        this.uploaderUId = uploaderUId;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public String getBrefIntro() {
        return brefIntro;
    }

    public void setBrefIntro(String brefIntro) {
        this.brefIntro = brefIntro;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
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

    public float getFileSize() {
        return fileSize;
    }

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }

    public int getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(int uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getNowVersion() {
        return nowVersion;
    }

    public void setNowVersion(String nowVersion) {
        this.nowVersion = nowVersion;
    }

    public ArrayList<String> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<String> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<String> getVersionList() {
        return versionList;
    }

    public void setVersionList(ArrayList<String> versionList) {
        this.versionList = versionList;
    }
}

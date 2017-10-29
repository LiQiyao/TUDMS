package com.nokia.tudms.beans.upload;

import java.util.ArrayList;

/**
 * Created by Lee on 2017/4/29.
 * 上传工具信息的bean
 */
public class UploadToolBean {

    private String toolName;
    private int uploaderUId;
    private int categoryId;
    private String briefIntro;
    private String description;
    private int uploadTime;
    private String version;
    private float fileSize;
    private String filePosition;
    private ArrayList<String> tagList = new ArrayList<String>();

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public int getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(int uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public float getFileSize() {
        return fileSize;
    }

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePosition() {
        return filePosition;
    }

    public void setFilePosition(String filePosition) {
        this.filePosition = filePosition;
    }

    public ArrayList<String> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<String> tagList) {
        this.tagList = tagList;
    }

    public void addTag(String tagName){
        tagList.add(tagName);
    }
}

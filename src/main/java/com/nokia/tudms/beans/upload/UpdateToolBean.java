package com.nokia.tudms.beans.upload;

/**
 * Created by Lee on 2017/5/3.
 * 更新工具的Bean
 */
public class UpdateToolBean {
    private int toolId;
    private float fileSize;
    private int uploadTime;
    private String version;
    private String filePosition;

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFilePosition() {
        return filePosition;
    }

    public void setFilePosition(String filePosition) {
        this.filePosition = filePosition;
    }
}

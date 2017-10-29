package com.nokia.tudms.beans.tool;

/**
 * @Author Rin
 * @Date 2017/4/30
 */
public class VersionBean {
    private int fileId;
    private String versionName;
    private float size;
    private int uploadTime;
    private String downloadLink;

    public VersionBean() {
    }

    public VersionBean(int fileId, String versionName, float size, int uploadTime, String downloadLink) {
        this.fileId = fileId;
        this.versionName = versionName;
        this.size = size;
        this.uploadTime = uploadTime;
        this.downloadLink = downloadLink;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(int uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }
}

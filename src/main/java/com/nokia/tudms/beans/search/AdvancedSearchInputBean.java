package com.nokia.tudms.beans.search;

/**
 * Created by Lee on 2017/4/29.
 * 高级搜索输入信息的bean
 */
public class AdvancedSearchInputBean {

    private String toolNameKey = "";
    private String uploaderName = "";
    private int uploaderTimeSt = 0;
    private int uploaderTimeEn = 0;
    private String tagName = "";
    private int category = 0;

    public String getToolNameKey() {
        return toolNameKey;
    }

    public void setToolNameKey(String toolNameKey) {
        this.toolNameKey = toolNameKey;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public int getUploaderTimeSt() {
        return uploaderTimeSt;
    }

    public void setUploaderTimeSt(int uploaderTimeSt) {
        this.uploaderTimeSt = uploaderTimeSt;
    }

    public int getUploaderTimeEn() {
        return uploaderTimeEn;
    }

    public void setUploaderTimeEn(int uploaderTimeEn) {
        this.uploaderTimeEn = uploaderTimeEn;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}

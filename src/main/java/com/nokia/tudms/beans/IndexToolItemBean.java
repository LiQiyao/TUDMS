package com.nokia.tudms.beans;

/**
 * @Author Rin
 * @Date 2017/4/24
 */
public class IndexToolItemBean {
    private int toolId;
    private int toolType;
    private String toolName;
    private double toolSize;
    private String toolBriefIntro;
    private int toolZanCount;
    private int toolDownloadCount;
    private int toolCommentCount;

    public IndexToolItemBean(){}
    public IndexToolItemBean(int toolId, int toolType, String toolName, double toolSize, String toolBriefIntro, int toolZanCount, int toolDownloadCount, int toolCommentCount) {
        this.toolId = toolId;
        this.toolType = toolType;
        this.toolName = toolName;
        this.toolSize = toolSize;
        this.toolBriefIntro = toolBriefIntro;
        this.toolZanCount = toolZanCount;
        this.toolDownloadCount = toolDownloadCount;
        this.toolCommentCount = toolCommentCount;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public int getToolType() {
        return toolType;
    }

    public void setToolType(int toolType) {
        this.toolType = toolType;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public double getToolSize() {
        return toolSize;
    }

    public void setToolSize(double toolSize) {
        this.toolSize = toolSize;
    }

    public String getToolBriefIntro() {
        return toolBriefIntro;
    }

    public void setToolBriefIntro(String toolBriefIntro) {
        this.toolBriefIntro = toolBriefIntro;
    }

    public int getToolZanCount() {
        return toolZanCount;
    }

    public void setToolZanCount(int toolZanCount) {
        this.toolZanCount = toolZanCount;
    }

    public int getToolDownloadCount() {
        return toolDownloadCount;
    }

    public void setToolDownloadCount(int toolDownloadCount) {
        this.toolDownloadCount = toolDownloadCount;
    }

    public int getToolCommentCount() {
        return toolCommentCount;
    }

    public void setToolCommentCount(int toolCommentCount) {
        this.toolCommentCount = toolCommentCount;
    }
}

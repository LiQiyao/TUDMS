package com.nokia.tudms.beans.usercenter;

/**
 * Created by Lee on 2017/5/4.
 * 关注的工具
 */
public class AttentionTool {
    private int toolId;
    private String toolName;
    private String newestVersion;
    private int newestVersionId;
    private String newestVersionTime;

    public int getToolId() {
        return toolId;
    }

    public int getNewestVersionId() {
        return newestVersionId;
    }

    public void setNewestVersionId(int newestVersionId) {
        this.newestVersionId = newestVersionId;
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

    public String getNewestVersion() {
        return newestVersion;
    }

    public void setNewestVersion(String newestVersion) {
        this.newestVersion = newestVersion;
    }

    public String getNewestVersionTime() {
        return newestVersionTime;
    }

    public void setNewestVersionTime(String newestVersionTime) {
        this.newestVersionTime = newestVersionTime;
    }
}

package com.nokia.tudms.beans.tool;

/**
 * @Author Rin
 * @Date 2017/5/6
 */
public class DetailPageHelperBean {
    private boolean logged;
    private boolean follow;
    private boolean uploader;
    private boolean adminAuth;

    public DetailPageHelperBean(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public boolean isUploader() {
        return uploader;
    }

    public void setUploader(boolean uploader) {
        this.uploader = uploader;
    }

    public boolean isAdminAuth() {
        return adminAuth;
    }

    public void setAdminAuth(boolean adminAuth) {
        this.adminAuth = adminAuth;
    }
}

package com.macys.sdt.projects.Architecture.MSH.utils.db.models;


public class UserPwd {

    private String userId;
    private Integer hintId;
    private String hintAnswer;
    private String lastModified;
    private String created;
    private Integer siteId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Integer getHintId() {
        return hintId;
    }

    public void setHintId(Integer hintId) {
        this.hintId = hintId;
    }

    public String getHintAnswer() {
        return hintAnswer;
    }

    public void setHintAnswer(String hintAnswer) {
        this.hintAnswer = hintAnswer;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }


    @Override
    public String toString() {
        return userId + "," + userId + "," + hintId + "," + hintAnswer + "," + lastModified + "," + created + "," + siteId;
    }

}










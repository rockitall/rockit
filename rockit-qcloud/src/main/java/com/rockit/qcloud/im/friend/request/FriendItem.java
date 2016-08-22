package com.rockit.qcloud.im.friend.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Allen on 2016/8/14.
 */
public class FriendItem {
    @JsonProperty("To_Account")
    private String identifier;

    @JsonProperty("Remark")
    private String remark;

    @JsonProperty("RemarkTime")
    private long remarkTime;

    @JsonProperty("GroupName")
    private List<String> groups;

    @JsonProperty("AddSource")
    private String addSource;

    @JsonProperty("AddWording")
    private String addWording;

    @JsonProperty("AddTime")
    private long addTime;

    @JsonProperty("CustomItem")
    private List<CustomItem> customItems;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getRemarkTime() {
        return remarkTime;
    }

    public void setRemarkTime(long remarkTime) {
        this.remarkTime = remarkTime;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getAddSource() {
        return addSource;
    }

    public void setAddSource(String addSource) {
        this.addSource = addSource;
    }

    public String getAddWording() {
        return addWording;
    }

    public void setAddWording(String addWording) {
        this.addWording = addWording;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public List<CustomItem> getCustomItems() {
        return customItems;
    }

    public void setCustomItems(List<CustomItem> customItems) {
        this.customItems = customItems;
    }

    public static class CustomItem {
        private String tag;
        private String value;
    }
}

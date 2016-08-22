package com.rockit.qcloud.im.friend.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockit.qcloud.im.common.QCloudApiRequestBody;

import java.util.List;

/**
 * Created by Allen on 2016/8/14.
 */
public class AddFriendRequestBody implements QCloudApiRequestBody {
    @JsonProperty("From_Account")
    private String indentifier;

    @JsonProperty("AddFriendItem")
    private List<FriendItem> friends;

    public String getIndentifier() {
        return indentifier;
    }

    public void setIndentifier(String indentifier) {
        this.indentifier = indentifier;
    }

    public List<FriendItem> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendItem> friends) {
        this.friends = friends;
    }
}

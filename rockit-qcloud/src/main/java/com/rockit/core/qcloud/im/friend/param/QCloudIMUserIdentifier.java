package com.rockit.core.qcloud.im.friend.param;

/**
 * Created by Allen on 2016/8/14.
 */
public class QCloudIMUserIdentifier {
    private String identifier;
    private String usersig;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getUsersig() {
        return usersig;
    }

    public void setUsersig(String usersig) {
        this.usersig = usersig;
    }
}

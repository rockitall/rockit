package com.rockit.qcloud.im.friend.service;

import com.rockit.qcloud.im.common.QCloudApiRequest;
import com.rockit.qcloud.im.common.QCloudHttp;
import com.rockit.qcloud.im.common.QCloudProperties;
import com.rockit.qcloud.im.friend.param.QCloudIMUserIdentifier;
import com.rockit.qcloud.im.friend.request.FriendItem;
import com.rockit.qcloud.im.friend.request.AddFriendRequestBody;
import com.rockit.qcloud.im.friend.response.AddFriendResponse;
import com.rockit.qcloud.im.friend.response.CheckFriendResponse;
import com.rockit.qcloud.im.friend.response.DeleteFriendResponse;

import java.util.List;

/**
 * Created by Allen on 2016/8/13.
 */

public class FriendsheepService {
    private QCloudHttp http;
    private QCloudProperties qCloudProperties;

    public AddFriendResponse addFriend(QCloudIMUserIdentifier user, List<FriendItem> friends) {
        QCloudApiRequest request = new QCloudApiRequest();
        request.setIdentifier(user.getIdentifier());
        request.setSdkappid(qCloudProperties.getSdkAppId());
        request.setUsersig(user.getUsersig());

        AddFriendRequestBody body = new AddFriendRequestBody();
        body.setFriends(friends);
        body.setIndentifier(user.getIdentifier());
        request.setBody(body);
        return http.post("/v4/sns/friend_import", request, AddFriendResponse.class, 1000);
    }

    public DeleteFriendResponse deleteFriend(QCloudIMUserIdentifier user, QCloudApiRequest request) {
        return http.post("/v4/sns/friend_delete", request, DeleteFriendResponse.class, 1000);
    }

    public CheckFriendResponse checkFriend(QCloudApiRequest request) {
        return http.post("/", request, CheckFriendResponse.class, 1000);
    }

    public QCloudHttp getHttp() {
        return http;
    }

    public void setHttp(QCloudHttp qCloudHttp) {
        this.http = qCloudHttp;
    }
}

package com.rockit.core.qcloud.im.friend.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockit.core.qcloud.im.common.QCloudApiResponse;

import java.util.List;

/**
 * Created by Allen on 2016/8/14.
 */
public class AddFriendResponse extends QCloudApiResponse {
    @JsonProperty("ResultItem")
    private List<ResultItem> resultItems;

    @JsonProperty("Fail_Account")
    private List<String> failAccounts;

    @JsonProperty("Invalid_Account")
    private List<String> invalidAccounts;

    @JsonProperty("ActionStatus")
    private String actionStatus;


    public static class ResultItem {
        private String identifer;
        private int resultCode;
        private String resultInfo;
    }
}

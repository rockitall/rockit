package com.rockit.core.qcloud.im.common;


import com.rockit.core.utils.JacksonUtil;

/**
 * Created by Allen on 2016/8/13.
 */
public interface QCloudApiRequestBody {
    default String getAsString() {
        return JacksonUtil.toJson(this);
    }
}

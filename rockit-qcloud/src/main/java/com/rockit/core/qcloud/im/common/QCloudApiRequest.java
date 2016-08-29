package com.rockit.core.qcloud.im.common;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Allen on 2016/8/13.
 */
public class QCloudApiRequest {
    private String sdkappid;
    private String identifier;
    private String usersig;
    private int random;
    public QCloudApiRequestBody body;

    public QCloudApiRequest() {
        random = ThreadLocalRandom.current().nextInt(100, 100000);
    }

    public String getSdkappid() {
        return sdkappid;
    }

    public void setSdkappid(String sdkappid) {
        this.sdkappid = sdkappid;
    }

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

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public QCloudApiRequestBody getBody() {
        return body;
    }

    public void setBody(QCloudApiRequestBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "QCloudApiRequest{" +
                "sdkappid='" + sdkappid + '\'' +
                ", identifier='" + identifier + '\'' +
                ", usersig='" + usersig + '\'' +
                ", random=" + random +
                ", body=" + (body != null ? body.getAsString() : null) +
                '}';
    }
}

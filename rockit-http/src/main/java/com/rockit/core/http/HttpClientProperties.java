package com.rockit.core.http;

public class HttpClientProperties {
    private int maxTotal = 1000;
    private int defaultMaxPerRoute = 100;
    private int soTimeoutInMillis = 1000;
    private int connectionTimeoutInMillis = 5000;
    private int socketTimeoutInMillis = 1000;
    private boolean userProxy;

    public boolean isUserProxy() {
        return userProxy;
    }

    public void setUserProxy(boolean userProxy) {
        this.userProxy = userProxy;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public int getSoTimeoutInMillis() {
        return soTimeoutInMillis;
    }

    public void setSoTimeoutInMillis(int soTimeoutInMillis) {
        this.soTimeoutInMillis = soTimeoutInMillis;
    }

    public int getConnectionTimeoutInMillis() {
        return connectionTimeoutInMillis;
    }

    public void setConnectionTimeoutInMillis(int connectionTimeoutInMillis) {
        this.connectionTimeoutInMillis = connectionTimeoutInMillis;
    }

    public int getSocketTimeoutInMillis() {
        return socketTimeoutInMillis;
    }

    public void setSocketTimeoutInMillis(int socketTimeoutInMillis) {
        this.socketTimeoutInMillis = socketTimeoutInMillis;
    }
}

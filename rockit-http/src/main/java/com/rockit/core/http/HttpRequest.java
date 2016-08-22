package com.rockit.core.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

public class HttpRequest {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    private int connectTimeout = -1;
    private int socketTimeout = -1;
    private int connectionRequestTimeout = -1;

    private SortedMap<String, String> bodyParams;
    private SortedMap<String, String> urlParams;
    private SortedMap<String, String> headers;
    private HttpMethod httpMethod;
    private String requestUrl;
    private HttpEntity bodyEntity;

    public static HttpRequest post(String url) {
        return new HttpRequest(url, HttpMethod.POST);
    }

    public static HttpRequest get(String url) {
        return new HttpRequest(url, HttpMethod.GET);
    }

    private HttpRequest(String url, HttpMethod method) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url must not be null");
        }
        if (method == null) {
            throw new IllegalArgumentException("method must not be null");
        }
        this.httpMethod = method;

        try {
            int i = url.indexOf('?');
            if (i < 0) {
                this.requestUrl = url;
                return;
            }

            this.requestUrl = url.substring(0, i);
            String qstr = url.substring(i + 1);
            StringTokenizer tokenizer = new StringTokenizer(qstr, "&");
            String[] keyVal;
            while (tokenizer.hasMoreElements()) {
                keyVal = tokenizer.nextToken().split("=");
                if (keyVal.length == 2 && StringUtils.isNoneBlank(keyVal)) {
                    this.addUrlParameter(keyVal[0], keyVal[1]);
                }
            }
        } catch (Exception e) {
            logger.error("parse url:{} error", url);
        }
    }

    public HttpRequest addHeader(String key, String value) {
        this.headers = put(this.headers, key, value);
        return this;
    }

    public HttpRequest removeHeader(String key) {
        if (this.headers != null) {
            this.headers.remove(key);
        }
        return this;
    }

    public SortedMap<String, String> getHeaders() {
        return this.headers;
    }

    public HttpRequest addUrlParameter(String key, String value) {
        this.urlParams = put(urlParams, key, value);
        return this;
    }

    public HttpRequest addUrlParameters(Map<String, String> urlParamMap) {
        this.urlParams = putAll(urlParamMap, urlParams);
        return this;
    }

    public SortedMap<String, String> getUrlParameters() {
        return urlParams;
    }

    public HttpRequest addBodyParameter(String key, String value) {
        this.bodyParams = put(bodyParams, key, value);
        return this;
    }

    public HttpRequest addBodyParameters(Map<String, String> bodyParamMap) {
        this.bodyParams = putAll(bodyParamMap, this.bodyParams);
        return this;
    }

    public void removeParam(String key) {
        if (urlParams != null) {
            urlParams.remove(key);
        }
        if (bodyParams != null) {
            bodyParams.remove(key);
        }
    }

    public String getParam(String key) {
        if (urlParams != null && urlParams.containsKey(key)) {
            return urlParams.get(key);
        }
        if (bodyParams != null) {
            return bodyParams.get(key);
        }
        return null;
    }

    public boolean hasParam(String key) {
        return (urlParams != null && urlParams.containsKey(key)) || (bodyParams != null && bodyParams.containsKey(key));
    }


    public SortedMap<String, String> getBodyParameters() {
        return bodyParams;
    }

    public SortedMap<String, String> getAllParameters() {
        SortedMap<String, String> paramMap = new TreeMap<String, String>();
        if (urlParams != null) {
            paramMap.putAll(urlParams);
        }
        if (bodyParams != null) {
            paramMap.putAll(bodyParams);
        }
        return paramMap;
    }

    public HttpRequest connectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public HttpRequest socketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public HttpRequest connectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
        return this;
    }


    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    private static SortedMap<String, String> put(SortedMap<String, String> map, String key, String value) {
        if (map == null) {
            map = new TreeMap<String, String>();
        }
        map.put(key, value);
        return map;
    }

    private static SortedMap<String, String> putAll(Map<String, String> srcMap, SortedMap<String, String> destMap) {
        if (destMap == null) {
            destMap = new TreeMap<String, String>();
        }
        srcMap.putAll(destMap);
        return destMap;
    }

    public HttpEntity getBodyEntity() {
        return bodyEntity;
    }

    public void setBodyEntity(HttpEntity bodyEntity) {
        this.bodyEntity = bodyEntity;
    }
}

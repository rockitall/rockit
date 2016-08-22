package com.rockit.qcloud.im.common;

import com.rockit.core.utils.JacksonUtil;
import com.rockit.core.http.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by Allen on 2016/8/13.
 */
public class QCloudHttp {
    private QCloudProperties qCloudProperties;
    private HttpClientService httpClientService;

    public <T extends QCloudApiResponse> T post(String path, QCloudApiRequest request, Class<T> responseClazz, int timeout) {
        StringBuilder buf = new StringBuilder(256);
        buf.append(qCloudProperties.getQCloudApiDomain());
        buf.append(path);
        buf.append("?sdkappid=").append(request.getSdkappid());
        buf.append("&identifier=").append(request.getIdentifier());
        buf.append("&usersig").append(request.getUsersig());
        buf.append("&random").append(request.getRandom());

        String url = buf.toString();

        HttpRequest req = HttpRequest.post(url);
        QCloudApiRequestBody body = request.getBody();
        String bodyContent = null;
        if (body != null) {
            bodyContent = body.getAsString();
        }
        if (StringUtils.isBlank(bodyContent)) {
            bodyContent = "{}";
        }
        try {
            req.setBodyEntity(new StringEntity(bodyContent));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("unsupported encoding");
        }

        try {
            HttpResponse response = httpClientService.execute(req);
            String responseContent = response.getBody();
            if (StringUtils.isBlank(responseContent)) {
                throw new HttpRuntimeException("call tencent api service error, path:" + path + ", response is empty");
            }

            return JacksonUtil.toObject(responseContent, responseClazz);
        } catch (Exception e) {
            throw new HttpRuntimeException("call tencent api service error, path:" + path, e);
        }
    }

    public QCloudProperties getqCloudProperties() {
        return qCloudProperties;
    }

    public void setqCloudProperties(QCloudProperties qCloudProperties) {
        this.qCloudProperties = qCloudProperties;
    }

    public HttpClientService getHttpClientService() {
        return httpClientService;
    }

    public void setHttpClientService(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }
}

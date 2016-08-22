package com.rockit.core.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Allen on 2016/7/31.
 */
public class HttpClientService {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientService.class);
    private HttpClientManager httpClientManager;

    public HttpClientService(HttpClientProperties httpClientProperties) {
        this.httpClientManager = new HttpClientManager(httpClientProperties);
    }

    public HttpResponse execute(HttpRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("http request must not be null");
        }

        // Transform marmot request to apache http request.
        HttpUriRequest httpRequest = transformRequest(request, httpClientManager.getHttpClientProperties());

        CloseableHttpResponse httpResponse = null;
        HttpResponse response = null;
        try {
            // Execute http request.
            httpResponse = httpClientManager.getHttpClient().execute(httpRequest);

            // Transform apache http response to marmot response.
            response = transformResponse(httpResponse);

            return response;
        } catch (Exception e) {
            throw new HttpRuntimeException(e);
        } finally {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    private HttpUriRequest transformRequest(HttpRequest request, HttpClientProperties httpClientProperties) {
        // Prepare request.
        RequestBuilder builder = RequestBuilder.create(request.getHttpMethod().name());

        // Prepare url parameters.
        String url = buildUrl(request.getRequestUrl(), request.getUrlParameters());
        builder.setUri(url);

        // Prepare post body.
        HttpEntity entity = request.getBodyEntity();
        if (entity == null) {
            entity = buildEntity(request.getBodyParameters());
        }
        builder.setEntity(entity);

        // Prepare default http headers.

        builder.addHeader("Accept-Encoding", "gzip, deflate");
//        builder.addHeader("X-Forwarded-For", getXForwardedFor());

        // Prepare custom headers.
        Map<String, String> headerMap = request.getHeaders();
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builder.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // Prepare request setting.
        RequestConfig requestConfig = getRequestConfig(request, httpClientProperties);
        builder.setConfig(requestConfig);

        HttpUriRequest uriRequest = builder.build();
        return uriRequest;
    }

    private HttpEntity buildEntity(SortedMap<String, String> bodyParameters) {
        return null;
    }

    private String buildUrl(String url, SortedMap<String, String> params) {
        return null;
    }

    private static RequestConfig getRequestConfig(HttpRequest request, HttpClientProperties httpClientProperties) {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setRedirectsEnabled(true);
        builder.setConnectTimeout(httpClientProperties.getSoTimeoutInMillis());
        builder.setSocketTimeout(httpClientProperties.getSocketTimeoutInMillis());
        builder.setConnectionRequestTimeout(httpClientProperties.getConnectionTimeoutInMillis());

        if (request.getConnectTimeout() >= 0) {
            builder.setConnectTimeout(request.getConnectTimeout());
        }
        if (request.getSocketTimeout() >= 0) {
            builder.setSocketTimeout(request.getSocketTimeout());
        }
        if (request.getConnectionRequestTimeout() >= 0) {
            builder.setConnectionRequestTimeout(request.getConnectionRequestTimeout());
        }

        if (httpClientProperties.isUserProxy()) {
            HttpHost proxy = getProxy();
            builder.setProxy(proxy);
        }
        return builder.build();
    }

    private static HttpHost getProxy() {
        String proxyHost = System.getProperty("http.proxyHost");
        String proxyPort = System.getProperty("http.proxyPort");
        if (StringUtils.isNotBlank(proxyHost) && StringUtils.isNotBlank(proxyPort)) {
            int port = Integer.parseInt(proxyPort);
            HttpHost httpHost = new HttpHost(proxyHost, port);
            return httpHost;
        }
        return null;
    }

    private static HttpResponse transformResponse(CloseableHttpResponse httpResponse) throws IOException {
        HttpResponse response = new HttpResponse();
        int status = httpResponse.getStatusLine().getStatusCode();
        response.setStatus(status);

        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            byte[] bytes = EntityUtils.toByteArray(entity);
            response.setBytes(bytes);
        }
        return response;
    }
}

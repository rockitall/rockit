package com.rockit.core.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Allen on 2016/7/31.
 */
public class HttpClientManager {
    private PoolingHttpClientConnectionManager manager;
    private HttpClientProperties httpClientProperties;
    private CloseableHttpClient httpClient;

    public HttpClientManager(HttpClientProperties httpClientProperties) {
        Registry<ConnectionSocketFactory> registry = null;
        try {
            registry = this.createRegistry();
        } catch (Exception e) {
            e.printStackTrace();
        }
        manager = new PoolingHttpClientConnectionManager(registry);
        manager.setDefaultMaxPerRoute(httpClientProperties.getDefaultMaxPerRoute());
        SocketConfig config = SocketConfig.custom().setSoTimeout(httpClientProperties.getSoTimeoutInMillis()).build();
        manager.setDefaultSocketConfig(config);

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(httpClientProperties.getConnectionTimeoutInMillis()).setSocketTimeout(httpClientProperties.getSocketTimeoutInMillis()).build();
        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(manager).build();
        this.httpClientProperties = httpClientProperties;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    private Registry<ConnectionSocketFactory> createRegistry() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, (chain, authType) -> true).build();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
        return socketFactoryRegistry;
    }

    public PoolingHttpClientConnectionManager getManager() {
        return manager;
    }

    public HttpClientProperties getHttpClientProperties() {
        return httpClientProperties;
    }
}

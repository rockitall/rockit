package com.rockit.core.http;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class HttpResponse {
    private int status;
    private byte[] bytes;
    private String body;

    public int getStatus() {
        return status;
    }

    public HttpResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public HttpResponse setBytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }

    public String getBody() {
        if (body != null) {
            return body;
        }

        if (bytes == null) {
            return null;
        }

        try {
            body = new String(bytes, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return body;
    }
}

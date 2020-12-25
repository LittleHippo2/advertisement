package com.haol.advertisement.entity;

import lombok.Data;

@Data
public class HttpResponse {

    private int page;

    private int size;

    private int total;

    private Object data;

    private String message;

    private int code;

    public HttpResponse(int page, int size, int total, Object data, String message, int code) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public HttpResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}

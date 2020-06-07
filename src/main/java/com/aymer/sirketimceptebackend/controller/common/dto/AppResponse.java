package com.aymer.sirketimceptebackend.controller.common.dto;

import lombok.Getter;

@Getter
public class AppResponse<T> {
    private Integer status;
    private T data;
    private String message;

    public static AppResponse nullResponse() {
        return new AppResponse(null);
    }

    public AppResponse(T data) {
        this.status = 200;
        this.data = data;
    }

    public AppResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}

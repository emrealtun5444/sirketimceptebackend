package com.aymer.sirketimceptebackend.common.api.dto;

import lombok.Getter;

@Getter
public class AppResponse<T> {
    private Integer status;
    private T data;
    private String errorMessage;

    public static AppResponse nullResponse() {
        return new AppResponse(null);
    }

    public AppResponse(T data) {
        this.status = 200;
        this.data = data;
    }

    public AppResponse() {
        this.status = 200;
    }

    public AppResponse(Integer status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public void setData(T data) {
        this.data = data;
    }
}

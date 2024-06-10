package com.grassroots.cineflow.service;

public class BusinessResult<T> {

    private Boolean success;

    private String message;

    private T data;

    public static <T> BusinessResult<T> success() {
        BusinessResult<T> result = new BusinessResult<>();
        result.setSuccess(true);
        result.setMessage("操作成功");
        return result;
    }

    public static <T> BusinessResult<T> success(T data) {
        BusinessResult<T> result = new BusinessResult<>();
        result.setSuccess(true);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    public static <T> BusinessResult<T> success(String message, T data) {
        BusinessResult<T> result = new BusinessResult<>();
        result.setSuccess(true);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> BusinessResult<T> fail(String message) {
        BusinessResult<T> result = new BusinessResult<>();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

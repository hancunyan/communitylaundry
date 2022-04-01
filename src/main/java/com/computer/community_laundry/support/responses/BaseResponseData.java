package com.computer.community_laundry.support.responses;

/**
 * @description: 响应内容
 * @author: han
 */
public abstract class BaseResponseData<T> {
    /**
     * 响应内容
     */
    private T data;

    protected void setData(T data) {
        this.data = data;
    }

    /**
     * 响应具体内容
     */
    public abstract BaseResponseData setDataContext(T data);

    public T getData() {
        return data;
    }
}

package com.computer.community_laundry.support.responses.imlp;

import com.computer.community_laundry.support.responses.BaseResponseData;
import com.computer.community_laundry.support.responses.OptStatusCode;

/**
 * @description: 默认格式消息返回
 * @author: han
 */
public class ResponseData<T> extends BaseResponseData<T> {
    /**
     * 操作响应状态码
     */
    private OptStatusCode optStatusCode;

    /**
     * 响应提示消息
     */
    private String message;


    /**
     * 响应具体内容
     *
     * @param data
     */
    @Override
    public ResponseData setDataContext(T data) {
        this.setData(data);
        return this;
    }

    public ResponseData(OptStatusCode optStatusCode, String message) {
        this.optStatusCode = optStatusCode;
        this.message = message;
    }

    public ResponseData(OptStatusCode optStatusCode) {
        this.optStatusCode = optStatusCode;
    }

    /**
     * 返回状态码
     *
     * @return
     */
    public int getOptStatusCode() {
        return optStatusCode.getCode();
    }

    public void setOptStatusCode(OptStatusCode optStatusCode) {
        this.optStatusCode = optStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "optStatusCode=" + optStatusCode +
                ", message='" + message + '\'' +
                '}';
    }
}

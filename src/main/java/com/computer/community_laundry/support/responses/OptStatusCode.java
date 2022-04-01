package com.computer.community_laundry.support.responses;

/**
 * @description: 执行状态码
 * @author: han
 */
public enum OptStatusCode {
    /**
     * 操作成功
     */
    OPT_SUCCESS(10400),

    /**
     * 操作发生错误
     */
    OPT_ERROR(10500),

    /**
     * 校验错误
     */
    OPT_VERIFY_ERROR(10501),

    /**
     * 未知错误
     */
    OPT_UNKNOW_Error(10505);


    /**
     * 成员变量
     */
    private int code;

    OptStatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

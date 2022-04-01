package com.computer.community_laundry.support.responses.imlp;


import com.computer.community_laundry.support.responses.OptStatusCode;

/**
 * @description: 失败错误信息
 * @author: han
 */
public class FailedResponseData extends ResponseData {
    public FailedResponseData(String failedMessage) {
        super(OptStatusCode.OPT_ERROR, failedMessage);
    }

    public FailedResponseData() {
        super(OptStatusCode.OPT_ERROR, "failed");
    }
}

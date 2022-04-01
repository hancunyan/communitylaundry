package com.computer.community_laundry.support.responses.imlp;

import com.computer.community_laundry.support.responses.OptStatusCode;

/**
 * @description: 成功消息
 * @author: han
 */
public class SuccessResponseData extends ResponseData {
    public SuccessResponseData() {
        super(OptStatusCode.OPT_SUCCESS, "successful");
    }
}

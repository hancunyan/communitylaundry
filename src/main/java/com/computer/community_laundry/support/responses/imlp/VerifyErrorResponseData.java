package com.computer.community_laundry.support.responses.imlp;

import com.computer.community_laundry.support.responses.OptStatusCode;

/**
 * @description: 验证错误信息
 * @author: han
 */
public class VerifyErrorResponseData extends ResponseData {
    public VerifyErrorResponseData(String verifyErrorMessage) {
        super(OptStatusCode.OPT_VERIFY_ERROR, verifyErrorMessage);
    }
}

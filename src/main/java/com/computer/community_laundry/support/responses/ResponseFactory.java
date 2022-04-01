package com.computer.community_laundry.support.responses;


import com.computer.community_laundry.support.responses.imlp.FailedResponseData;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.computer.community_laundry.support.responses.imlp.SuccessResponseData;
import com.computer.community_laundry.support.responses.imlp.VerifyErrorResponseData;

/**
 * @description: 响应工厂
 * @author: han
 */
public final class ResponseFactory {
    public static ResponseData createCustomResponse(OptStatusCode optStatusCode, String message) {
        return new ResponseData(optStatusCode, message);
    }

    public static SuccessResponseData createSuccessResponse() {
        return new SuccessResponseData();
    }

    public static FailedResponseData createFaildResponse() {
        return new FailedResponseData();
    }

    public static FailedResponseData createFaildResponse(String message) {
        return new FailedResponseData(message);
    }

    public static VerifyErrorResponseData createVerifyErrorResponse(String verifyErrorMessage) {
        return new VerifyErrorResponseData(verifyErrorMessage);
    }
}

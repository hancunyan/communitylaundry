package com.computer.community_laundry.support.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: response xiangying
 */
@Getter
@Setter
@ToString
public class JsonData {
    private int code;
    private String message;

    public JsonData(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public JsonData() {
    }
}

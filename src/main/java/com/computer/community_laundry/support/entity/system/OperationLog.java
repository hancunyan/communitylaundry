package com.computer.community_laundry.support.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class OperationLog {
    private Integer id;

    private String operModul;

    private String operType;

    private String operDesc;

    private Integer operUserId;

    private String operUserName;

    private String operMethod;

    private String operUrl;

    private String operIp;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String operCreateTime;
}
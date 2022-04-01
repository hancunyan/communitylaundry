package com.computer.community_laundry.support.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OperationLogWithBLOBs extends OperationLog {
    private String operRequParam;

    private String operRespParam;

}
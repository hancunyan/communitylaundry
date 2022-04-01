package com.computer.community_laundry.support.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ExceptionLogWithBLOBs extends ExceptionLog {
    private String excRequParam;

    private String exeMessage;

}
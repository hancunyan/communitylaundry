package com.computer.community_laundry.support.entity.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: layui 分页响应实体
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LayUIPage {
    // 状态码
    private int code;
    // 响应消息
    private String msg;
    // 总条数
    private long count;
    // 数据
    private List data;
}

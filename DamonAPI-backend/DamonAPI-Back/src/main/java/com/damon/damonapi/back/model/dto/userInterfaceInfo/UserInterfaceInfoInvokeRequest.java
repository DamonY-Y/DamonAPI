package com.damon.damonapi.back.model.dto.userInterfaceInfo;

import lombok.Data;

@Data
public class UserInterfaceInfoInvokeRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String userRequestParams;
}

package com.damon.damonapi.back.model.dto.interfaceInfo;

import lombok.Data;

@Data
public class InterfaceInfoInvokeRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String userRequestParams;
}

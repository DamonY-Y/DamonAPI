package com.damon.damonapi.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.damonapi.common.model.entity.InterfaceInfo;


/**
* @author dell
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-04-02 21:48:28
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}

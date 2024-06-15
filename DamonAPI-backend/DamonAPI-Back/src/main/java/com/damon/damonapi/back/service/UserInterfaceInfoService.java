package com.damon.damonapi.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.damonapi.common.model.entity.UserInterfaceInfo;

/**
* @author dell
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-04-02 21:48:28
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {


    boolean invokeCount(long interfaceInfoId, long userId);

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean b);
}

package com.damon.damonapi.back.service.impl.inner;

import com.damon.damonapi.back.service.UserInterfaceInfoService;
import com.damonapi.common.service.InnerUserInterfaceInfoService;
import javax.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;


/**
 * 内部用户接口信息服务实现类
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}

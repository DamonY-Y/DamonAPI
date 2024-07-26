package com.damonapi.common.service;

import com.damonapi.common.model.entity.InterfaceInfo;

/**
 * @Description:TODO
 * @Author:damon
 * @Date:2024/7/26 15:04
 */
public interface InnerGoodService {

    /**
     * 从数据库中
     */
    Integer buyNormalGood(Integer userId, Integer goodId);

    Integer buySpikeGood(Integer userId, Integer goodId);
}

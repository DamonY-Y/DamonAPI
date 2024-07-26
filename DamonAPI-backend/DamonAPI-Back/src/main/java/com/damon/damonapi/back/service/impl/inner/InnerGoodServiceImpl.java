package com.damon.damonapi.back.service.impl.inner;

import com.damon.damonapi.back.mapper.GoodMapper;
import com.damon.damonapi.back.mapper.OrderMapper;
import com.damon.damonapi.back.mapper.UserMapper;
import com.damonapi.common.model.entity.Good;
import com.damonapi.common.model.entity.Order;
import com.damonapi.common.service.InnerGoodService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:TODO
 * @Author:damon
 * @Date:2024/7/26 15:56
 */
@DubboService
public class InnerGoodServiceImpl implements InnerGoodService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodMapper goodMapper;

    /**
     * 真正实现 商品下单的业务
     * @param userId
     * @param goodId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer buyNormalGood(Integer userId, Integer goodId) {
        // 1. 用户调用次数增加，余额减少
        Good good = goodMapper.selectById(goodId);
        BigDecimal price = good.getPrice();
        Long useNum = good.getUseNum();
        userMapper.updateAfterBuyGood(userId,price,useNum);

        // 2. 填写订单表
        Order order = new Order();
        order.setGoodId(goodId.longValue());
        order.setGoodId(userId.longValue());
        order.setCreateTime(new Date());
        return orderMapper.insert(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer buySpikeGood(Integer userId, Integer goodId) {
        // 1. 用户调用次数增加，余额减少
        Good good = goodMapper.selectById(goodId);
        BigDecimal price = good.getPrice();
        Long useNum = good.getUseNum();
        userMapper.updateAfterBuyGood(userId,price,useNum);

        // 2.扣减特价商品的库存
        int i = goodMapper.updateStock(goodId);

        // 填写订单表
        if (i > 0){
            Order order = new Order();
            order.setGoodId(goodId.longValue());
            order.setGoodId(userId.longValue());
            order.setCreateTime(new Date());
            return orderMapper.insert(order);
        }
        return 0;
    }
}

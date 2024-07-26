package com.damon.damonapi.orderweb.listener;

import com.damonapi.common.service.InnerGoodService;
import com.damonapi.common.service.InnerOrderService;
import com.damonapi.common.service.InnerUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @Description:OrderListener
 * @Author:damon
 */

@Component
@RocketMQMessageListener(topic = "damonApiOrderTopic",
        consumerGroup = "damonApi-consumer-group",
        consumeMode = ConsumeMode.CONCURRENTLY,
        consumeThreadNumber = 40
)
public class OrderListener implements RocketMQListener<MessageExt> {

    @DubboReference
    private InnerGoodService goodService;
//
    @Resource
    private RedisTemplate redisTemplate;

    int Redis_ZX_TIME = 20000;
    /**
     * 用户调用次数增加，余额减少
     * 如果是特价商品，还要扣减库存
     * 写订单表
     * @param message
     */
    @Override
    public void onMessage(MessageExt message) {
        String msg = new String(message.getBody());
        int userId = Integer.parseInt(msg.split("-")[0]);
        int goodId = Integer.parseInt(msg.split("-")[1]);
        int spike = Integer.parseInt(msg.split("-")[1]);

        // 如果是非特价商品，不需要考虑并发问题
        if (spike == 0){
            goodService.buyNormalGood(userId,goodId);
        }else{
            // 特价商品需要扣减库存，需要考虑并发问题
            // 使用 redis setnx 分布式锁  压力会分摊到redis和程序中执行  缓解db的压力
            int currentThreadTime = 0;
            while(true){
                Boolean flag = redisTemplate.opsForValue().setIfAbsent("lock:" + goodId, "", Duration.ofSeconds(30));
                if (flag) {
                    try{
                        goodService.buySpikeGood(userId,goodId);
                        return;
                    } finally {
                        redisTemplate.delete("lock:" + goodId);
                    }
                }else {
                    currentThreadTime += 200;
                    if (currentThreadTime > Redis_ZX_TIME){
                        return;
                    }
                    try{
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}

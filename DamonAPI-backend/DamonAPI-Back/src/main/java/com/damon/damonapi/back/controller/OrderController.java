package com.damon.damonapi.back.controller;

import com.damon.damonapi.back.common.BaseResponse;
import com.damon.damonapi.back.common.ResultUtils;
import com.damon.damonapi.back.service.GoodService;
import com.damon.damonapi.back.service.OrderService;
import com.damon.damonapi.back.service.UserService;
import com.damonapi.common.model.entity.Good;
import com.damonapi.common.model.entity.Order;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:OrderController
 * @Author:damon
 * @Date:2024/7/26 11:35
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private UserService userService;

    @Resource
    private OrderService orderService;

    @Resource
    private GoodService goodService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("confirm")
    public BaseResponse<String> confirm(@RequestParam("goodId") Integer goodId,@RequestParam("userID") Integer userID){

        Good good = goodService.getById(goodId);
        // 1. 判断用户余额是否大于商品价格
        if(userService.getById(userID).getLeftMoney().compareTo(good.getPrice()) < 0){
            return ResultUtils.success("账户余额不足");
        }
        // 2. 判断该商品是否为特价商品
        Integer spike = good.getSpike();
        String uk = userID + "-" + goodId + "-" + spike;
        // todo 非特价商品的处理方式，直接异步处理
        if (spike == 0){

        }
        // 特价商品处理方式：redis
        else if (spike == 1){
            // redis 实现 一人一次购买
            Boolean flag = redisTemplate.opsForValue().setIfAbsent("uk:" + uk, "");
            if (!flag){
                return ResultUtils.success("请勿重复购买");
            }

            Long count = redisTemplate.opsForValue().decrement("goodId:" + goodId);
            if (count < 0){
                // 保证 redis 的库存最小值是 0
                redisTemplate.opsForValue().increment("goodId:" + goodId);
                return ResultUtils.success("特价商品已售空");
            }
            // 放入mq 异步处理
            rocketMQTemplate.asyncSend("damonApiOrderTopic", uk,new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("message sent successfully");
                }
                @Override
                public void onException(Throwable throwable) {
                    System.out.println("failed to send message");
                    System.out.println(userID + "-" + goodId);
                }
            });
        }
        return ResultUtils.success("ok");
    }

}

package com.damon.damonapi.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.damon.damonapi.back.mapper.OrderMapper;
import com.damon.damonapi.back.service.OrderService;
import com.damonapi.common.model.entity.Order;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【order(虚拟商品)】的数据库操作Service实现
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService {

}





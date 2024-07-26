package com.damon.damonapi.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.damon.damonapi.back.mapper.GoodMapper;
import com.damon.damonapi.back.service.GoodService;
import com.damonapi.common.model.entity.Good;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【good(虚拟商品)】的数据库操作Service实现
* @createDate 2024-07-26 11:26:52
*/
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good>
    implements GoodService {

}





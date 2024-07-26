package com.damon.damonapi.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.damonapi.common.model.entity.Good;
import org.apache.ibatis.annotations.Update;


/**
* @author Administrator
* @description 针对表【good(虚拟商品)】的数据库操作Mapper
* @createDate 2024-07-26 11:26:52
* @Entity generator.domain.Good
*/
public interface GoodMapper extends BaseMapper<Good> {

    @Update("update good set stocks = stocks - 1 ,update_time = now() where id = #{goodId} and stocks - 1 >= 0")
    int updateStock(Integer goodId);
}





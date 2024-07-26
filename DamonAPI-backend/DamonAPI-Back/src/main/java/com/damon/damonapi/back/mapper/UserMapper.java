package com.damon.damonapi.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.damonapi.common.model.entity.User;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;


/**
* @author Administrator
* @description 针对表【user(用户)】的数据库操作Mapper
*/
public interface UserMapper extends BaseMapper<User> {

    @Update("update  user set leftNum = leftNum + #{useNum} ,leftMoney = leftMoney - #{price} where id = #{userId}")
    void updateAfterBuyGood(Integer userId, BigDecimal price, Long useNum);
}





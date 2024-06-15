package com.damon.damonapi.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.damonapi.common.model.entity.User;

import javax.servlet.http.HttpServletRequest;


/**
* @author dell
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-04-02 21:48:28
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount, String userPassword, String checkPassword);

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);
}

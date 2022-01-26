package com.wry.service;

import com.wry.domain.User;

import java.util.List;

public interface IUserService {
    //查询所有用户列表
    List<User> findUsers() throws Exception;

    //锁定用户
    int delUser(Integer id) throws Exception;
}

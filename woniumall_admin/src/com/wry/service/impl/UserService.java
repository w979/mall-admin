package com.wry.service.impl;

import com.wry.dao.UserDao;
import com.wry.domain.User;
import com.wry.domain.UserExample;
import com.wry.service.IUserService;
import com.wry.utils.MybatisUtils;

import java.util.List;

public class UserService implements IUserService {
    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    @Override
    public List<User> findUsers() throws Exception {
        UserDao userDao = MybatisUtils.getDao(UserDao.class);
        return userDao.selectByExample(new UserExample());
    }

    /**
     * 锁定用户
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int delUser(Integer id) throws Exception {
        UserDao userDao = MybatisUtils.getDao(UserDao.class);
        return userDao.delUser(id);
    }
}

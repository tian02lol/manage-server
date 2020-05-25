package com.example.demo.biz.impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.biz.UserBiz;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBizImpl implements UserBiz {
    @Autowired
    private UserDao userDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserEntity record) {
        return userDao.insert(record);
    }

    @Override
    public int insertSelective(UserEntity record) {
        return userDao.insertSelective(record);
    }

    @Override
    public UserEntity selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserEntity record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserEntity record) {
        return userDao.updateByPrimaryKey(record);
    }

    @Override
    public UserEntity selectByUserName(String userName) {
        return userDao.selectByUserName(userName);
    }
}

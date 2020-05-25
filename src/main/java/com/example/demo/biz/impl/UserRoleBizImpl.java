package com.example.demo.biz.impl;

import com.example.demo.entity.UserRoleEntity;
import com.example.demo.biz.UserRoleBiz;
import com.example.demo.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleBizImpl implements UserRoleBiz {

    @Autowired
    private UserRoleDao userRoleDao;
    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userRoleDao.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(UserRoleEntity record) {
        return userRoleDao.insert(record);
    }

    @Override
    public int insertSelective(UserRoleEntity record) {
        return userRoleDao.insertSelective(record);
    }

    @Override
    public UserRoleEntity selectByPrimaryKey(Integer userId) {
        return userRoleDao.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserRoleEntity record) {
        return userRoleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserRoleEntity record) {
        return userRoleDao.updateByPrimaryKey(record);
    }
}

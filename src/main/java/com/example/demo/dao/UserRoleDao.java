package com.example.demo.dao;

import com.example.demo.entity.UserRoleEntity;

public interface UserRoleDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserRoleEntity record);

    int insertSelective(UserRoleEntity record);

    UserRoleEntity selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserRoleEntity record);

    int updateByPrimaryKey(UserRoleEntity record);
}
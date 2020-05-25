package com.example.demo.biz;

public interface UserRoleBiz {
    int deleteByPrimaryKey(Integer userId);

    int insert(com.example.demo.entity.UserRoleEntity record);

    int insertSelective(com.example.demo.entity.UserRoleEntity record);

    com.example.demo.entity.UserRoleEntity selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(com.example.demo.entity.UserRoleEntity record);

    int updateByPrimaryKey(com.example.demo.entity.UserRoleEntity record);
}

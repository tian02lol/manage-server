package com.example.demo.biz;

import com.example.demo.entity.UserEntity;

import java.util.List;

public interface RoleBiz {
    int deleteByPrimaryKey(Integer id);

    int insert(com.example.demo.entity.RoleEntity record);

    int insertSelective(com.example.demo.entity.RoleEntity record);

    com.example.demo.entity.RoleEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.example.demo.entity.RoleEntity record);

    int updateByPrimaryKey(com.example.demo.entity.RoleEntity record);

    List<UserEntity> selectAll();
}

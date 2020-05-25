package com.example.demo.biz;

import org.springframework.stereotype.Service;

public interface UserBiz {
    int deleteByPrimaryKey(Integer id);

    int insert(com.example.demo.entity.UserEntity record);

    int insertSelective(com.example.demo.entity.UserEntity record);

    com.example.demo.entity.UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.example.demo.entity.UserEntity record);

    int updateByPrimaryKey(com.example.demo.entity.UserEntity record);

    com.example.demo.entity.UserEntity selectByUserName(String userName);

}

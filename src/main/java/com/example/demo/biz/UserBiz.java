package com.example.demo.biz;

import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserBiz {
    int deleteByPrimaryKey(Integer id);

    int insert(com.example.demo.entity.UserEntity record);

    int insertSelective(com.example.demo.entity.UserEntity record);

    com.example.demo.entity.UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.example.demo.entity.UserEntity record);

    int updateByPrimaryKey(com.example.demo.entity.UserEntity record);

    com.example.demo.entity.UserEntity selectByUserName(String userName);

    List<UserEntity> selectAll();

}

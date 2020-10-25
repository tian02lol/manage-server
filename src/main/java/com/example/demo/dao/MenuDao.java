package com.example.demo.dao;

import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.UserEntity;

import java.util.List;

public interface MenuDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuEntity record);

    int insertSelective(MenuEntity record);

    MenuEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuEntity record);

    int updateByPrimaryKey(MenuEntity record);
    List<MenuEntity> selectAll();
}
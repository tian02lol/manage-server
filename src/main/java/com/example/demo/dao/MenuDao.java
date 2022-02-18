package com.example.demo.dao;

import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuEntity record);

    int insertSelective(MenuEntity record);

    MenuEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuEntity record);

    int updateByPrimaryKey(MenuEntity record);
    List<MenuEntity> selectAll();
}
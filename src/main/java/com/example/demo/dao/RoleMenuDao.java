package com.example.demo.dao;

import com.example.demo.entity.RoleMenu;

public interface RoleMenuDao {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);
}
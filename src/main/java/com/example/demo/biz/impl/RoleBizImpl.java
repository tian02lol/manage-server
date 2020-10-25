package com.example.demo.biz.impl;

import com.example.demo.entity.RoleEntity;
import com.example.demo.biz.RoleBiz;
import com.example.demo.dao.RoleDao;
import com.example.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleBizImpl implements RoleBiz {

    @Autowired
    private RoleDao roleDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoleEntity record) {
        return roleDao.insert(record);
    }

    @Override
    public int insertSelective(RoleEntity record) {
        return roleDao.insertSelective(record);
    }

    @Override
    public RoleEntity selectByPrimaryKey(Integer id) {
        return roleDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleEntity record) {
        return roleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleEntity record) {
        return roleDao.updateByPrimaryKey(record);
    }

    @Override
    public List<UserEntity> selectAll() {
        return roleDao.selectAll();
    }
}

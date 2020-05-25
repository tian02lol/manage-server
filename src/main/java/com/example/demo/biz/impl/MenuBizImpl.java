package com.example.demo.biz.impl;

import com.example.demo.biz.MenuBiz;
import com.example.demo.dao.MenuDao;
import com.example.demo.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuBizImpl implements MenuBiz {

    @Autowired
    private MenuDao menuDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return menuDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MenuEntity record) {
        return menuDao.insert(record);
    }

    @Override
    public int insertSelective(MenuEntity record) {
        return menuDao.insertSelective(record);
    }

    @Override
    public MenuEntity selectByPrimaryKey(Integer id) {
        return menuDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MenuEntity record) {
        return menuDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MenuEntity record) {
        return menuDao.updateByPrimaryKey(record);
    }
}

package com.example.demo.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserEntity;
import com.example.demo.biz.UserBiz;
import com.example.demo.dao.UserDao;
import com.example.demo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBizImpl implements UserBiz {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserEntity record) {
        return userDao.insert(record);
    }

    @Override
    public int insertSelective(UserEntity record) {
        return userDao.insertSelective(record);
    }

    @Override
    public UserEntity selectByPrimaryKey(Integer id) {
        //取redis数据
        if(redisUtils.hasKey(id+"")){
            UserEntity user = JSONObject.toJavaObject((JSON) redisUtils.get(id+""),UserEntity.class);
            return (user) ;
        } else {
            UserEntity user = userDao.selectByPrimaryKey(id);
            redisUtils.set(id+"",user);
            return user;
        }
    }

    @Override
    public int updateByPrimaryKeySelective(UserEntity record) {
        //修改后删除缓存
        redisUtils.SyncValue(record.getId()+"");
        return userDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserEntity record) {
        //修改后删除缓存
        redisUtils.SyncValue(record.getId()+"");
        return userDao.updateByPrimaryKey(record);
    }

    @Override
    public UserEntity selectByUserName(String userName) {
        return userDao.selectByUserName(userName);
    }

    @Override
    public List<UserEntity> selectAll() {
        return userDao.selectAll();
    }
}

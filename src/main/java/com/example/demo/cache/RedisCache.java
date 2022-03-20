package com.example.demo.cache;

import com.example.demo.utils.BeanUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

/**
 * 配置mybatis二级缓存
 */
public class RedisCache implements Cache {

    private final String id;

    public RedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        getRedisTemplate().opsForHash().put(id,getKeyToMd5(o.toString()),o1);
    }

    @Override
    public Object getObject(Object o) {
        return getRedisTemplate().opsForHash().get(id,getKeyToMd5(o.toString()));
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        getRedisTemplate().opsForHash().delete(id);
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }


    private RedisTemplate getRedisTemplate(){
        return (RedisTemplate) BeanUtils.getBean("redisTemplate");
    }


    private String getKeyToMd5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}

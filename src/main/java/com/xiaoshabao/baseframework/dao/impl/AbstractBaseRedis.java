package com.xiaoshabao.baseframework.dao.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
/**
 * redis基础类
 */
public abstract class AbstractBaseRedis<K, V> {
	@Autowired  
    protected RedisTemplate<K, V> redisTemplate;  
  
    /** 
     * 设置redisTemplate 
     */  
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
      
    /** 
     * 获取 RedisSerializer 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }  
}

package com.xiaoshabao.baseframework.component.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis配置
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
  @Value("${redis.host}")
  private String host;
  @Value("${redis.port}")
  private int port;
  @Value("${redis.password}")
  private String password;
  @Value("${redis.maxIdle}")
  private String maxIdle;
  @Value("${redis.minIdle}")
  private String minIdle;
  @Value("${redis.maxActive}")
  private String maxActive;
  @Value("${redis.maxWait}")
  private int maxWait;
  @Value("${redis.testOnBorrow}")
  private boolean testOnBorrow=true;

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
    redisConnectionFactory.setHostName(host);
    redisConnectionFactory.setPort(port);
    if(StringUtils.isNotEmpty(password))
      redisConnectionFactory.setPassword(password);
    redisConnectionFactory.setTimeout(maxWait);
    return redisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
    redisTemplate.setConnectionFactory(cf);
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

    // Number of seconds before expiration. Defaults to unlimited (0)  
    cacheManager.setDefaultExpiration(3000); // Sets the default expire time (in seconds)  
    return cacheManager;
  }
}

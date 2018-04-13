package cn.devcenter.framework.cache.redis.config;

import cn.devcenter.framework.cache.redis.config.property.RedisPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 配置
 *
 * @author gaosong
 *
 */
@Configuration
public class RedisPoolConfig {

    @Autowired
    private RedisPoolProperties redisPoolProperties;

    @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisPoolProperties.getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisPoolProperties.getMaxWaitMillis());
        jedisPoolConfig.setMaxIdle(redisPoolProperties.getMaxIdle());
        jedisPoolConfig.setNumTestsPerEvictionRun(redisPoolProperties.getNumTestsPerEvictionRun());
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(
                redisPoolProperties.getTimeBetweenEvictionRunsMillis());
        jedisPoolConfig
                .setMinEvictableIdleTimeMillis(redisPoolProperties.getMinEvictableIdleTimeMillis());
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(
                redisPoolProperties.getSoftMinEvictableIdleTimeMillis());
        jedisPoolConfig.setTestOnBorrow(redisPoolProperties.isTestOnBorrow());
        jedisPoolConfig.setTestWhileIdle(redisPoolProperties.isTestWhileIdle());
        jedisPoolConfig.setBlockWhenExhausted(redisPoolProperties.isBlockWhenExhausted());
        return jedisPoolConfig;
    }

}

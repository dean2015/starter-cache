package cn.devcenter.framework.cache.redis.config;

import cn.devcenter.framework.cache.redis.config.property.RedisClusterNodeProperties;
import cn.devcenter.framework.cache.redis.config.property.RedisClusterProperties;
import cn.devcenter.framework.cache.redis.config.property.RedisConnectionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 配置
 *
 * @author gaosong
 */
@Configuration
public class RedisClusterConfig {

    @Autowired
    private RedisConnectionProperties redisConnectionProperties;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private RedisClusterProperties redisClusterProperties;

    @Autowired
    private RedisClusterNodeProperties redisClusterNodeProperties;

    @Bean
    public RedisClusterConfiguration getRedisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisClusterNodeProperties.getNodes());
        redisClusterConfiguration.setMaxRedirects(redisClusterProperties.getMaxRedirects());
        return redisClusterConfiguration;
    }

    @Bean
    public JedisConnectionFactory getJedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = null;
        if (redisClusterProperties.isEnabled()) {
            jedisConnectionFactory = new JedisConnectionFactory(getRedisClusterConfiguration());
        } else {
            jedisConnectionFactory = new JedisConnectionFactory();
            jedisConnectionFactory.setHostName(redisConnectionProperties.getHost());
            jedisConnectionFactory.setPort(redisConnectionProperties.getPort());
            jedisConnectionFactory.setTimeout(redisConnectionProperties.getTimeout());
            jedisConnectionFactory.setDatabase(redisConnectionProperties.getDatabaseIndex());
            jedisConnectionFactory.setUsePool(redisConnectionProperties.isUsePool());
            jedisConnectionFactory.setPassword(redisConnectionProperties.getPassword());
        }
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }

}

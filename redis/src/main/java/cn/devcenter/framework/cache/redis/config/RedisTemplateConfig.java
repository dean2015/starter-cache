package cn.devcenter.framework.cache.redis.config;

import cn.devcenter.framework.cache.redis.NativeRedisTemplate;
import cn.devcenter.framework.cache.redis.StringObjectRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 配置
 *
 * @author gaosong
 */
@Configuration
public class RedisTemplateConfig {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public <T> StringObjectRedisTemplate<T> getStringByteRedisTemplate() {
        return new StringObjectRedisTemplate<T>(jedisConnectionFactory);
    }

    @Bean
    public <K, V> NativeRedisTemplate<K, V> getNativeRedisTemplate() {
        return new NativeRedisTemplate<K, V>(jedisConnectionFactory);
    }

}

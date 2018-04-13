package cn.devcenter.framework.cache.redis.config;

import cn.devcenter.framework.cache.redis.NativeRedisTemplate;
import cn.devcenter.framework.cache.redis.RedisCache;
import cn.devcenter.framework.cache.redis.DefaultRedisCache;
import cn.devcenter.framework.cache.redis.StringObjectRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public <K, V> RedisCache<K, V> getNativeRedisCache(@Autowired NativeRedisTemplate<K, V> nativeRedisTemplate) {
        return new RedisCache<K, V>(nativeRedisTemplate);
    }

    @Bean
    public <V> DefaultRedisCache<V> getRedisStringObjectCache(@Autowired StringObjectRedisTemplate<V> stringObjectRedisTemplate) {
        return new DefaultRedisCache<V>(stringObjectRedisTemplate);
    }

}

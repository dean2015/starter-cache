package cn.devcenter.framework.cache.redis;

import cn.devcenter.framework.cache.redis.serializer.SerializableValueSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class StringObjectRedisTemplate<V> extends RedisTemplate<String, V> {

    public StringObjectRedisTemplate(RedisConnectionFactory connectionFactory) {
        setConnectionFactory(connectionFactory);
        SerializableValueSerializer<V> objectSerializer = new SerializableValueSerializer<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        setKeySerializer(stringRedisSerializer);
        setValueSerializer(objectSerializer);
        setHashKeySerializer(stringRedisSerializer);
        setHashValueSerializer(objectSerializer);
        afterPropertiesSet();
    }

}

package cn.devcenter.framework.cache.redis;

import cn.devcenter.framework.cache.redis.serializer.SerializableValueSerializer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class NativeRedisTemplate<K, V> extends RedisTemplate<K, V> {

    public NativeRedisTemplate(RedisConnectionFactory connectionFactory) {
        setConnectionFactory(connectionFactory);
        SerializableValueSerializer<K> keySerializer = new SerializableValueSerializer<>();
        SerializableValueSerializer<V> valueSerializer = new SerializableValueSerializer<>();
        setKeySerializer(keySerializer);
        setValueSerializer(valueSerializer);
        setHashKeySerializer(keySerializer);
        setHashValueSerializer(valueSerializer);
        afterPropertiesSet();
    }

}

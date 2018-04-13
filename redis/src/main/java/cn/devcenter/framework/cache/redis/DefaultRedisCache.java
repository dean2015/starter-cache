package cn.devcenter.framework.cache.redis;

import cn.devcenter.model.cache.Cache;
import cn.devcenter.model.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Set;

public class DefaultRedisCache<T> implements Cache<String, T> {

    private RedisCache<String, T> redisCache;

    public DefaultRedisCache(RedisTemplate redisTemplate) {
        this.redisCache = new RedisCache<>(redisTemplate);
    }

    @Override
    public String put(String key, T value) throws CacheException {
        return redisCache.put(key, value);
    }

    @Override
    public String put(String key, T value, Long expiredTime) throws CacheException {
        return redisCache.put(key, value, expiredTime);
    }

    @Override
    public boolean putIfAbsent(String key, T value) throws CacheException {
        return redisCache.putIfAbsent(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends T> kvmap) throws CacheException {
        redisCache.putAll(kvmap);
    }

    @Override
    public T get(String key) throws CacheException {
        return redisCache.get(key);
    }

    @Override
    public Map<String, T> getAll(Set<? extends String> keyset) throws CacheException {
        return redisCache.getAll(keyset);
    }

    @Override
    public T getAndPut(String key, T value) throws CacheException {
        return redisCache.getAndPut(key, value);
    }

    @Override
    public boolean containsKey(String key) throws CacheException {
        return redisCache.containsKey(key);
    }

    @Override
    public boolean remove(String key) throws CacheException {
        return redisCache.remove(key);
    }

    @Override
    public T getAndRemove(String key) throws CacheException {
        return redisCache.getAndRemove(key);
    }

    @Override
    public void removeAll(Set<? extends String> keyset) throws CacheException {
        redisCache.removeAll(keyset);
    }

    @Override
    public void clear() throws CacheException {
        redisCache.clear();
    }
}

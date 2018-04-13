package cn.devcenter.framework.cache.redis;

import cn.devcenter.model.cache.Cache;
import cn.devcenter.model.cache.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @param <K>
 * @param <V>
 */
@Slf4j
public class RedisCache<K, V> implements Cache<K, V> {

    private RedisTemplate redisTemplate;

    public RedisCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public K put(K key, V value) throws CacheException {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            String errorMessage = "Set key[" + key + "] - value[" + value + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
        return key;
    }

    @Override
    public K put(K key, V value, Long expireIn) throws CacheException {
        try {
            redisTemplate.opsForValue().set(key, value, expireIn, TimeUnit.SECONDS);
        } catch (Exception e) {
            String errorMessage = "Set key[" + key + "] - value[" + value + "] with timeout[" + expireIn + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
        return key;
    }

    @Override
    public boolean putIfAbsent(K key, V value) throws CacheException {
        try {
            redisTemplate.opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            String errorMessage = "Set key[" + key + "] - value[" + value + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
        return true;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) throws CacheException {
        try {
            redisTemplate.opsForValue().multiSet(map);
        } catch (Exception e) {
            String errorMessage = "Set map[" + map + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
    }

    @Override
    public V get(K key) throws CacheException {
        try {
            return (V) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            String errorMessage = "Get key[" + key + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
    }

    @Override
    public Map<K, V> getAll(Set<? extends K> set) throws CacheException {
        Map<K, V> resultMap = new HashMap<>();
        set.forEach(key -> {
            try {
                resultMap.put(key, get(key));
            } catch (Exception e) {
                String errorMessage = "Get key[" + key + "] from set[" + set + "] in error.";
                log.error(errorMessage, e);
                return;
            }
        });
        return resultMap;
    }

    @Override
    public V getAndPut(K key, V value) throws CacheException {
        try {
            return (V) redisTemplate.opsForValue().getAndSet(key, value);
        } catch (Exception e) {
            String errorMessage = "GetAndSet key[" + key + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
    }

    @Override
    public boolean containsKey(K key) throws CacheException {
        try {
            return redisTemplate.opsForValue().size(key) > 0L;
        } catch (Exception e) {
            String errorMessage = "contains key[" + key + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
    }

    @Override
    public boolean remove(K key) throws CacheException {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            String errorMessage = "remove key[" + key + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
        return true;
    }

    @Override
    public V getAndRemove(K key) throws CacheException {
        try {
            V result = get(key);
            redisTemplate.delete(key);
            return result;
        } catch (Exception e) {
            String errorMessage = "getAndRemove key[" + key + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
    }

    @Override
    public void removeAll(Set<? extends K> set) throws CacheException {
        try {
            Set<K> keyset = new HashSet<>();
            set.forEach(key -> {
                keyset.add(key);
            });
            redisTemplate.delete(keyset);
        } catch (Exception e) {
            String errorMessage = "removeAll key[" + set + "] in error.";
            log.error(errorMessage, e);
            throw new CacheException(errorMessage, e);
        }
    }

    @Override
    public void clear() throws CacheException {
        throw new CacheException("Not supported");
    }
}

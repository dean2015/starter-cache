package cn.devcenter.framework.cache.redis.serializer;

import cn.devcenter.framework.cache.redis.util.SerializationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

@Slf4j
public class SerializableValueSerializer<T> implements RedisSerializer<T> {

    @Override
    public byte[] serialize(T object) throws SerializationException {
        if (object == null) {
            return SerializationUtils.EMPTY_ARRAY;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                log.debug("序列化对象IO错误", e);
            }
            throw new SerializationException("Could not write Object: " + e.getMessage(), e);
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }

                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                if (log.isDebugEnabled()) {
                    log.debug("关闭流错误", e);
                }
            }
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (SerializationUtils.isEmpty(bytes)) {
            return null;
        }
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("反序列化对象IO错误", e);
            }
            throw new SerializationException("Could not read Object: " + e.getMessage(), e);
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                if (log.isDebugEnabled()) {
                    log.debug("关闭流错误", e);
                }
            }
        }
    }

}

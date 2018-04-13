package cn.devcenter.framework.cache.redis.util;

public class SerializationUtils {

    public static final byte[] EMPTY_ARRAY = new byte[0];

    public static boolean isEmpty(byte[] data) {
        return data == null || data.length == 0;
    }

}

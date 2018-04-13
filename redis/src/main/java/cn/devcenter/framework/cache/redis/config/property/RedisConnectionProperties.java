package cn.devcenter.framework.cache.redis.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Redis cache 配置项获取类
 *
 * @author gaosong
 */
@ConfigurationProperties(prefix = RedisConnectionProperties.PREFIX)
@Data
public class RedisConnectionProperties {

    public static final String PREFIX = "cache.redis";

    private String host = "127.0.0.1";

    private int port = 6379;

    private String password = null;

    private int timeout = 5000;

    private boolean usePool = true;

    private int databaseIndex = 0;

}

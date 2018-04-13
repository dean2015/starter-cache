package cn.devcenter.framework.cache.redis.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Redis cache 配置项获取类
 *
 * @author gaosong
 */
@ConfigurationProperties(prefix = RedisClusterProperties.PREFIX)
@Data
public class RedisClusterProperties {

    public static final String PREFIX = "cache.redis.cluster";

    private boolean enabled = true;

    private int maxRedirects = 5;

    private String password = null;

    private int timeout = 5000;

}

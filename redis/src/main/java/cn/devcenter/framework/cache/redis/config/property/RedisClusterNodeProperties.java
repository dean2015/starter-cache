package cn.devcenter.framework.cache.redis.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis cache 配置项获取类
 *
 * @author gaosong
 */
@ConfigurationProperties(prefix = RedisClusterNodeProperties.PREFIX)
@Data
public class RedisClusterNodeProperties {

    public static final String PREFIX = "spring.redis.cluster";

    private List<String> nodes = new ArrayList<>();

}

package cn.devcenter.framework.cache.redis.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis pool 配置项获取类
 *
 * @author gaosong
 *
 */
@ConfigurationProperties(prefix = RedisPoolProperties.PREFIX)
@Data
public class RedisPoolProperties {

    public static final String PREFIX = "cache.redis.pool";

    // 最大连接数
    private int maxTotal = 30;
    // 最大空闲时间
    private int maxIdle = 10;
    // 最小空闲时间
    private int minIdle = 0;
    // 每次最大连接数
    private int numTestsPerEvictionRun = 1024;
    // 释放扫描的扫描间隔
    private int timeBetweenEvictionRunsMillis = 30000;
    // 连接的最小空闲时间
    private int minEvictableIdleTimeMillis = 1800000;
    // 连接控歘按时间多久后释放，当空闲时间>该值且空闲连接>最大空闲连接数时直接释放
    private int softMinEvictableIdleTimeMillis = 10000;
    // 获得链接时的最大等待毫秒数，小于0：阻塞不确定时间，默认-1
    private int maxWaitMillis = 1500;
    // 在获得链接的时候检查有效性，默认false
    private boolean testOnBorrow = true;
    // 在空闲时检查有效性，默认false
    private boolean testWhileIdle = true;
    // 连接耗尽时是否阻塞，false报异常，true阻塞超时,默认true
    private boolean blockWhenExhausted = true;

}

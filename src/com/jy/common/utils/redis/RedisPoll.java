package com.jy.common.utils.redis;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoll {
    /** 日志实例 */
    private static final Logger logger = Logger.getLogger(RedisPoll.class);

    private static JedisPool jedisPool = null;

    static {
        jedisPool = RedisPoll.getInstance();
    }

    public synchronized static JedisPool getInstance() {
        if (jedisPool == null) {
            try {
                Properties pps = new Properties();
                InputStream is = RedisPoll.class.getClassLoader().getResourceAsStream("redis.properties");
                pps.load(is);
                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMaxIdle(Integer.parseInt(pps.getProperty(
                        "maxIdle").trim()));
                jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(pps
                        .getProperty("maxWaitTime").trim()));
                jedisPoolConfig.setMaxTotal(Integer.parseInt(pps.getProperty(
                        "maxTotal").trim()));
                jedisPoolConfig.setTestOnBorrow(true);
                if(StringUtils.isBlank(pps.getProperty("password"))) {
                    jedisPool = new JedisPool(jedisPoolConfig, pps.getProperty(
                            "host").trim(), Integer.parseInt(pps
                            .getProperty("port").trim()));
                } else {
                    jedisPool = new JedisPool(jedisPoolConfig, pps.getProperty(
                            "host").trim(), Integer.parseInt(pps
                            .getProperty("port").trim()), Integer.parseInt(pps.getProperty("soTimeOut")), pps.getProperty("password"));
                }

            } catch (Exception e) {
                logger.error(e);
            }
        }
        return jedisPool;
    }

    public static void destroy() {
        jedisPool.destroy();
    }
}

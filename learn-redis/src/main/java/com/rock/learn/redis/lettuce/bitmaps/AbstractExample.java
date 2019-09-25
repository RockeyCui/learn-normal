package com.rock.learn.redis.lettuce.bitmaps;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

import java.io.IOException;
import java.util.Properties;

/**
 * @author cuishilei
 * @date 2019/9/19
 */
public abstract class AbstractExample {
    protected static RedisClient redisClient;
    protected static StatefulRedisConnection<String, String> connection;
    protected static RedisStringCommands<String, String> stringCommands;

    static {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        redisClient = RedisClient.create(properties.getProperty("redis.address"));
        connection = redisClient.connect();
        stringCommands = connection.sync();
        connection.sync();
    }

    protected static String getStringBitStr(String value) {
        StringBuilder sb = new StringBuilder();
        char[] strToChar = value.toCharArray();
        for (char c : strToChar) {
            sb.append(Integer.toBinaryString(c)).append(" ");
        }
        return sb.toString();
    }
}

package com.rock.learn.redis.lettuce.string;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisKeyCommands;
import io.lettuce.core.api.sync.RedisStringCommands;

import java.io.IOException;
import java.util.Properties;

/**
 * @author cuishilei
 * @date 2019/9/19
 */
public abstract class AbstractStringTest {
    protected static RedisClient redisClient;
    protected static StatefulRedisConnection<String, String> connection;
    protected static RedisCommands<String, String> commands;
    protected static RedisStringCommands<String, String> stringCommands;
    protected static RedisAsyncCommands<String, String> asyncCommands;
    protected static RedisKeyCommands<String, String> keyCommands;

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
        commands = connection.sync();
        asyncCommands = connection.async();
        keyCommands = connection.sync();
    }

}

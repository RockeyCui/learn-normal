package com.rock.learn.redis.lettuce.hash;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisHashAsyncCommands;
import io.lettuce.core.api.async.RedisListAsyncCommands;
import io.lettuce.core.api.sync.RedisHashCommands;
import io.lettuce.core.api.sync.RedisKeyCommands;
import io.lettuce.core.api.sync.RedisListCommands;

import java.io.IOException;
import java.util.Properties;

/**
 * @author cuishilei
 * @date 2019/9/19
 */
public abstract class AbstractHashTest {
    protected static RedisClient redisClient;
    protected static StatefulRedisConnection<String, String> connection;
    protected static RedisHashCommands<String, String> commands;
    protected static RedisHashAsyncCommands<String, String> asyncCommands;
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
        commands = connection.sync();
        asyncCommands = connection.async();
        keyCommands = connection.sync();
    }

}

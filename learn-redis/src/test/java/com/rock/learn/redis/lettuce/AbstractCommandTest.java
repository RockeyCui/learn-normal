package com.rock.learn.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.async.RedisHashAsyncCommands;
import io.lettuce.core.api.async.RedisListAsyncCommands;
import io.lettuce.core.api.async.RedisSetAsyncCommands;
import io.lettuce.core.api.sync.*;

import java.io.IOException;
import java.util.Properties;

/**
 * @author cuishilei
 */
public abstract class AbstractCommandTest {
    protected static RedisClient redisClient;
    protected static StatefulRedisConnection<String, String> connection;
    protected static RedisStringCommands<String, String> stringCommands;
    protected static RedisAsyncCommands<String, String> stringAsyncCommands;
    protected static RedisHashCommands<String, String> hashCommands;
    protected static RedisHashAsyncCommands<String, String> hashAsyncCommands;
    protected static RedisListCommands<String, String> listCommands;
    protected static RedisListAsyncCommands<String, String> listAsyncCommands;
    protected static RedisSetCommands<String, String> setCommands;
    protected static RedisSetAsyncCommands<String, String> setAsyncCommands;

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
        stringAsyncCommands = connection.async();

        hashCommands = connection.sync();
        hashAsyncCommands = connection.async();

        listCommands = connection.sync();
        listAsyncCommands = connection.async();

        setCommands = connection.sync();
        setAsyncCommands = connection.async();

        keyCommands = connection.sync();
    }

}

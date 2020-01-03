package com.rock.learn.redis.lettuce.string;

import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.ScanArgs;

/**
 * @author cuishilei
 * @date 2019/9/18
 */
public class LettuceStringSyncTest4 extends AbstractStringTest {

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        KeyScanCursor<String> scanCursor;
        scanCursor = keyCommands.scan(ScanArgs.Builder.limit(20000).match("REDIS_SUPPLIER_NUMBER_INTLONG*"));
        commands.mget(scanCursor.getKeys().toArray(new String[]{}));
        while (!scanCursor.isFinished()) {
            scanCursor = keyCommands.scan(scanCursor, ScanArgs.Builder.limit(20000).match("REDIS_SUPPLIER_NUMBER_INTLONG*"));
            commands.mget(scanCursor.getKeys().toArray(new String[]{}));
        }
        System.out.println("time " + (System.currentTimeMillis() - b));
        connection.close();
        redisClient.shutdown();
    }
}

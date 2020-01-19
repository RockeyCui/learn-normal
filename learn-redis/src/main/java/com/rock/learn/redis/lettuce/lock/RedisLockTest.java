package com.rock.learn.redis.lettuce.lock;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @author cuishilei
 */
public class RedisLockTest extends AbstractCommands {
    private final static ConcurrentMap<Thread, AtomicInteger> THREAD_DATA = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        String key = "lock";
        //唯一标识，释放锁时确保是自己持有的锁
        String value = UUID.randomUUID().toString();
        int lockTime = 10;

        MyLock myLock = new MyLock(commands, key, value);
        try {
            if (myLock.getLock(lockTime)) {
                System.out.println("---locked---");
                //模拟服务过程
                if (myLock.getLock(lockTime)) {
                    System.out.println("---relocked---");
                    myLock.release();
                }
                sleep(25000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myLock.release();
        }
    }
}

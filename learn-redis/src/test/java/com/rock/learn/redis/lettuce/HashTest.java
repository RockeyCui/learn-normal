package com.rock.learn.redis.lettuce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cuishilei
 */
public class HashTest extends AbstractCommandTest {
    @Before
    public void setUp() {
        connection.sync().flushdb();
    }

    @Test
    public void hset() {
        Assert.assertTrue(hashCommands.hset("key-heset", "one", "o"));
        Assert.assertFalse(hashCommands.hset("key-heset", "one", "o"));
    }


    @Test
    public void hsetnx() {
        Assert.assertTrue(hashCommands.hsetnx("key-hsetnx", "one", "o"));
        Assert.assertFalse(hashCommands.hsetnx("key-hsetnx", "one", "o"));
    }

    @Test
    public void hmset() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        Assert.assertEquals("OK", hashCommands.hmset("key-hmset", map));
    }

    @Test
    public void hdel() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        Assert.assertEquals("OK", hashCommands.hmset("key-hdel", map));
        Assert.assertEquals(2, hashCommands.hdel("key-hdel", "one", "two", "four").longValue());
    }

    @Test
    public void hincrby() {
        hashCommands.hsetnx("key-hincrby", "one", "1");
        Assert.assertEquals(11, hashCommands.hincrby("key-hincrby", "one", 10).longValue());
    }

    @Test
    public void hexists() {
        hashCommands.hsetnx("key-hexists", "one", "1");
        Assert.assertTrue(hashCommands.hexists("key-hexists", "one"));
        Assert.assertFalse(hashCommands.hexists("key-hexists", "two"));
        Assert.assertFalse(hashCommands.hexists("key-hexists-no", "one"));
    }

    @Test
    public void hget() {
        hashCommands.hsetnx("key-hget", "one", "1");
        Assert.assertEquals("1", hashCommands.hget("key-hget", "one"));
        Assert.assertNull(hashCommands.hget("key-hget", "two"));
    }

    @Test
    public void hgetall() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        Assert.assertEquals("OK", hashCommands.hmset("key-hgetall", map));

        Map<String, String> hgetall = hashCommands.hgetall("key-hgetall");
        for (Map.Entry<String, String> stringStringEntry : hgetall.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            Assert.assertEquals(value, map.get(key));
        }
    }

    @Test
    public void hkeys() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        hashCommands.hmset("key-hkeys", map);
        List<String> hkeys = hashCommands.hkeys("key-hkeys");
        Assert.assertArrayEquals(map.keySet().toArray(new String[]{}), hkeys.toArray(new String[]{}));
    }

    @Test
    public void hvals() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        hashCommands.hmset("key-hvals", map);
        List<String> hvals = hashCommands.hvals("key-hvals");
        Assert.assertArrayEquals(map.values().toArray(new String[]{}), hvals.toArray(new String[]{}));
    }

    @Test
    public void hlen() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        hashCommands.hmset("key-hlen", map);
        Assert.assertEquals(3, hashCommands.hlen("key-hlen").longValue());
    }

    @Test
    public void hstrlen() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "22");
        map.put("three", "333");
        hashCommands.hmset("key-hstrlen", map);
        Assert.assertEquals(2, hashCommands.hstrlen("key-hstrlen", "two").longValue());
    }
}

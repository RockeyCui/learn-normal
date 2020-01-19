package com.rock.learn.redis.lettuce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author cuishilei
 */
public class ListTest extends AbstractCommandTest {
    @Before
    public void setUp() {
        connection.sync().flushdb();
    }

    @Test
    public void rpush() {
        Assert.assertEquals(4, listCommands.rpush("key-rpush", "a", "b", "c", "d").longValue());
        List<String> res = listCommands.lrange("key-rpush", 0, -1);
        Assert.assertArrayEquals(new String[]{"a", "b", "c", "d"}, res.toArray(new String[]{}));
    }

    @Test
    public void rpushx() {
        Assert.assertEquals(0, listCommands.rpushx("key-rpushx", "a").longValue());
        listCommands.rpush("key-rpushx", "a");
        Assert.assertEquals(2, listCommands.rpushx("key-rpushx", "b").longValue());
        List<String> res = listCommands.lrange("key-rpushx", 0, -1);
        Assert.assertArrayEquals(new String[]{"a", "b"}, res.toArray(new String[]{}));
    }

    @Test
    public void lpush() {
        Assert.assertEquals(4, listCommands.lpush("key-lpush", "a", "b", "c", "d").longValue());
        List<String> res = listCommands.lrange("key-lpush", 0, -1);
        Assert.assertArrayEquals(new String[]{"d", "c", "b", "a"}, res.toArray(new String[]{}));
    }

    @Test
    public void lpushx() {
        Assert.assertEquals(0, listCommands.lpushx("key-lpushx", "a").longValue());
        listCommands.lpush("key-lpushx", "a");
        Assert.assertEquals(2, listCommands.lpushx("key-lpushx", "b").longValue());
        List<String> res = listCommands.lrange("key-lpushx", 0, -1);
        Assert.assertArrayEquals(new String[]{"b", "a"}, res.toArray(new String[]{}));
    }

    @Test
    public void rpop() {
        listCommands.rpush("key-rpop", "a", "b", "c", "d");
        Assert.assertEquals("d", listCommands.rpop("key-rpop"));
    }

    @Test
    public void lpop() {
        listCommands.rpush("key-lpop", "a", "b", "c", "d");
        Assert.assertEquals("a", listCommands.lpop("key-lpop"));
    }

    @Test
    public void ltrim() {
        listCommands.rpush("key-ltrim", "a", "b", "c", "d");
        Assert.assertEquals("OK", listCommands.ltrim("key-ltrim", 1, 2));
        List<String> res = listCommands.lrange("key-ltrim", 0, -1);
        Assert.assertArrayEquals(new String[]{"b", "c"}, res.toArray(new String[]{}));
    }

    @Test
    public void linsert() {
        listCommands.rpush("key-linsert", "a", "b", "c", "d");
        Assert.assertEquals(5, listCommands.linsert("key-linsert", false, "c", "c1").longValue());
        List<String> res = listCommands.lrange("key-linsert", 0, -1);
        Assert.assertArrayEquals(new String[]{"a", "b", "c", "c1", "d"}, res.toArray(new String[]{}));
        Assert.assertEquals(-1, listCommands.linsert("key-linsert", false, "e", "e1").longValue());
    }


    @Test
    public void lset() {
        listCommands.rpush("key-lset", "a", "b", "c", "d");
        Assert.assertEquals("OK", listCommands.lset("key-lset", 2, "c1"));
        List<String> res = listCommands.lrange("key-lset", 0, -1);
        Assert.assertArrayEquals(new String[]{"a", "b", "c1", "d"}, res.toArray(new String[]{}));
    }

    @Test
    public void lrem() {
        listCommands.rpush("key-lrem", "a", "b", "c", "c1", "c", "c", "d");
        Assert.assertEquals(2, listCommands.lrem("key-lrem", 2, "c").longValue());
        List<String> res = listCommands.lrange("key-lrem", 0, -1);
        Assert.assertArrayEquals(new String[]{"a", "b", "c1", "c", "d"}, res.toArray(new String[]{}));
    }

    @Test
    public void llen() {
        listCommands.rpush("key-llen", "a", "b", "c", "d");
        Assert.assertEquals(4, listCommands.llen("key-llen").longValue());
    }


    @Test
    public void lindex() {
        listCommands.rpush("key-lindex", "a", "b", "c", "d");
        Assert.assertEquals("c", listCommands.lindex("key-lindex", 2));
    }

    @Test
    public void lrange() {
        listCommands.rpush("key-lrange", "a", "b", "c", "d");
        List<String> res = listCommands.lrange("key-lrange", 0, 3);
        Assert.assertArrayEquals(new String[]{"a", "b", "c", "d"}, res.toArray(new String[]{}));
        res = listCommands.lrange("key-lrange", -2, -1);
        Assert.assertArrayEquals(new String[]{"c", "d"}, res.toArray(new String[]{}));
    }
}

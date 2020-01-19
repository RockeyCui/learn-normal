package com.rock.learn.redis.lettuce;

import io.lettuce.core.ScanArgs;
import io.lettuce.core.ValueScanCursor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author cuishilei
 */
public class SetTest extends AbstractCommandTest {
    @Before
    public void setUp() {
        connection.sync().flushdb();
    }

    @Test
    public void sadd() {
        Assert.assertEquals(2, setCommands.sadd("key-sadd", "a", "b").longValue());
        Assert.assertEquals(1, setCommands.sadd("key-sadd", "b", "c").longValue());
    }

    @Test
    public void scard() {
        setCommands.sadd("key-scard", "a", "b", "c", "c");
        Assert.assertEquals(3, setCommands.scard("key-scard").longValue());
    }

    @Test
    public void sdiff() {
        setCommands.sadd("key-sdiff-1", "a", "b", "c", "d");
        setCommands.sadd("key-sdiff-2", "c");
        setCommands.sadd("key-sdiff-3", "a", "c", "e");
        Set<String> sdiff = setCommands.sdiff("key-sdiff-1", "key-sdiff-2", "key-sdiff-3");
        Assert.assertArrayEquals(new String[]{"b", "d"}, sdiff.toArray(new String[]{}));
    }

    @Test
    public void sdiffstore() {
        setCommands.sadd("key-sdiffstore-1", "a", "b", "c", "d");
        setCommands.sadd("key-sdiffstore-2", "c");
        setCommands.sadd("key-sdiffstore-3", "a", "c", "e");
        Assert.assertEquals(2, setCommands.sdiffstore("temp", "key-sdiffstore-1", "key-sdiffstore-2", "key-sdiffstore-3").longValue());
        Assert.assertArrayEquals(new String[]{"b", "d"}, setCommands.smembers("temp").toArray(new String[]{}));
    }

    @Test
    public void sinter() {
        setCommands.sadd("key-sinter-1", "a", "b", "c", "d");
        setCommands.sadd("key-sinter-2", "c");
        setCommands.sadd("key-sinter-3", "a", "c", "e");
        Set<String> sinter = setCommands.sinter("key-sinter-1", "key-sinter-2", "key-sinter-3");
        Assert.assertArrayEquals(new String[]{"c"}, sinter.toArray(new String[]{}));
    }

    @Test
    public void sinterstore() {
        setCommands.sadd("key-sinterstore-1", "a", "b", "c", "d");
        setCommands.sadd("key-sinterstore-2", "c");
        setCommands.sadd("key-sinterstore-3", "a", "c", "e");
        Assert.assertEquals(1, setCommands.sinterstore("temp", "key-sinterstore-1", "key-sinterstore-2", "key-sinterstore-3").longValue());
        Assert.assertArrayEquals(new String[]{"c"}, setCommands.smembers("temp").toArray(new String[]{}));
    }

    @Test
    public void sismember() {
        setCommands.sadd("key-sismember", "a", "b", "c", "d");
        Assert.assertTrue(setCommands.sismember("key-sismember", "a"));
        Assert.assertFalse(setCommands.sismember("key-sismember", "e"));
    }

    @Test
    public void smembers() {
        Set<String> strings = new HashSet<>(Arrays.asList("a", "b", "c", "d"));
        setCommands.sadd("key-smembers", "a", "b", "c", "d");
        Set<String> smembers = setCommands.smembers("key-smembers");
        for (String smember : smembers) {
            Assert.assertTrue(strings.contains(smember));
        }
    }

    @Test
    public void smove() {
        setCommands.sadd("key-smove-1", "a", "b", "c", "d");
        setCommands.sadd("key-smove-2", "c");
        setCommands.sadd("key-smove-3", "a", "c", "e");
        Assert.assertTrue(setCommands.smove("key-smove-1", "key-smove-2", "a"));
        Assert.assertFalse(setCommands.smove("key-smove-1", "key-smove-2", "e"));
        Assert.assertTrue(setCommands.smove("key-smove-1", "key-smove-3", "c"));
    }


    @Test
    public void spop() {
        setCommands.sadd("key-spop", "a", "b", "c", "d");
        Set<String> spop = setCommands.spop("key-spop", 2);
        System.out.println(spop);
    }

    @Test
    public void srandmember() {
        setCommands.sadd("key-srandmember", "a", "b", "c", "d");
        List<String> srandmember = setCommands.srandmember("key-srandmember", 2);
        System.out.println(srandmember);
    }

    @Test
    public void srem() {
        setCommands.sadd("key-srem", "a", "b", "c", "d");
        Assert.assertEquals(2, setCommands.srem("key-srem", "a", "c", "e").longValue());
    }

    @Test
    public void sscan() {
        setCommands.sadd("key-sscan", "a1", "a2", "a3", "b");
        ScanArgs scanArgs = ScanArgs.Builder.matches("a*").limit(1);
        ValueScanCursor<String> scanCursor = setCommands.sscan("key-sscan", scanArgs);
        System.out.println(scanCursor.getValues());
        while (!scanCursor.isFinished()) {
            scanCursor = setCommands.sscan("key-sscan", scanCursor, scanArgs);
            System.out.println(scanCursor.getValues());
        }
    }

    @Test
    public void sunion() {
        setCommands.sadd("key-sunion-1", "a", "b", "c", "d");
        setCommands.sadd("key-sunion-2", "c");
        setCommands.sadd("key-sunion-3", "a", "c", "e");
        Set<String> sunion = setCommands.sunion("key-sunion-1", "key-sunion-2", "key-sunion-3");
        System.out.println(sunion);
    }

    @Test
    public void sunionstore() {
        setCommands.sadd("key-sunionstore-1", "a", "b", "c", "d");
        setCommands.sadd("key-sunionstore-2", "c");
        setCommands.sadd("key-sunionstore-3", "a", "c", "e");
        Assert.assertEquals(5, setCommands.sunionstore("temp", "key-sunionstore-1", "key-sunionstore-2", "key-sunionstore-3").longValue());
    }

}

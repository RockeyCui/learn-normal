
package com.rock.learn.redis.lettuce;

import io.lettuce.core.KeyValue;
import io.lettuce.core.SetArgs;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;


/**
 * @author cuishilei
 */

public class StringTest extends AbstractCommandTest {
    @Before
    public void setUp() {
        connection.sync().flushdb();
    }


    @Test
    public void set() throws InterruptedException {
        //保存成功返回 ok
        Assert.assertEquals("OK", stringCommands.set("key1", "value1"));
        //当键不存在设置成功
        Assert.assertNull(stringCommands.set("key1", "value1", SetArgs.Builder.nx()));
        //当键存在设置成功
        Assert.assertEquals("OK", stringCommands.set("key1", "value1", SetArgs.Builder.xx()));
        //ex 单位 s
        Assert.assertEquals("OK", stringCommands.set("k1", "v1", SetArgs.Builder.ex(2)));
        sleep(3000);
        Assert.assertNull(stringCommands.get("k1"));
        //px 单位 ms
        Assert.assertEquals("OK", stringCommands.set("k1", "v1", SetArgs.Builder.px(2000)));
        sleep(3000);
        Assert.assertNull(stringCommands.get("k1"));
    }

    @Test
    public void get() {
        Assert.assertEquals("OK", stringCommands.set("key1", "value1"));
        Assert.assertEquals("value1", stringCommands.get("key1"));
    }

    @Test
    public void del() {
        keyCommands.del("notExist");
        //删除不存在的键返回 0
        Assert.assertEquals(0, keyCommands.del("notExist").longValue());
        stringCommands.set("notExist", "ha");
        //删除成功返回 1
        Assert.assertEquals(1, keyCommands.del("notExist").longValue());
    }

    @Test
    public void setnx() {
        keyCommands.del("setnx");
        //set if not exist ，
        Assert.assertTrue(stringCommands.setnx("setnx", "nx"));
        Assert.assertFalse(stringCommands.setnx("setnx", "nx"));
    }

    @Test
    public void setex() throws InterruptedException {
        Assert.assertEquals("OK", stringCommands.setex("setex", 2, "ha"));
        sleep(3000);
        Assert.assertNull(stringCommands.get("setex"));
    }

    @Test
    public void getset() {
        stringCommands.set("getset", "value1");
        Assert.assertEquals("value1", stringCommands.getset("getset", "value2"));
        Assert.assertEquals("value2", stringCommands.getset("getset", "value1"));
    }

    @Test
    public void mset() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        Assert.assertEquals("OK", stringCommands.mset(map));
    }

    @Test
    public void mget() {
        mset();
        List<KeyValue<String, String>> mget = stringCommands.mget("one", "two");
        for (KeyValue<String, String> stringStringKeyValue : mget) {
            String key = stringStringKeyValue.getKey();
            String value = stringStringKeyValue.getValue();
            if ((!key.equals("one") || !value.equals("1")) && (!key.equals("two") || !value.equals("2"))) {
                Assert.fail("k " + key + " v " + value);
            }
        }
    }

    @Test
    public void incr() {
        Assert.assertEquals(1, stringCommands.incr("incr").longValue());
        Assert.assertEquals(2, stringCommands.incr("incr").longValue());
    }

    @Test
    public void incrby() {
        Assert.assertEquals(10, stringCommands.incrby("incrby", 10).longValue());
        Assert.assertEquals(20, stringCommands.incrby("incrby", 10).longValue());
    }

    @Test
    public void incrbyfloat() {
        Assert.assertEquals(100, stringCommands.incrbyfloat("incrbyfloat", 1e2).longValue());
        Assert.assertEquals(101.1, stringCommands.incrbyfloat("incrbyfloat", 1.1).longValue(), 0.1);
    }

    @Test
    public void decr() {
        Assert.assertEquals(-1, stringCommands.decr("decr").longValue());
        Assert.assertEquals(-2, stringCommands.decr("decr").longValue());
    }

    @Test
    public void decrby() {
        Assert.assertEquals(-10, stringCommands.decrby("decrby", 10).longValue());
        Assert.assertEquals(-12, stringCommands.decrby("decrby", 2).longValue());
    }


    @Test
    public void append() {
        Assert.assertEquals(1, stringCommands.append("append", "A").longValue());
        Assert.assertEquals("A", stringCommands.get("append"));
        Assert.assertEquals(2, stringCommands.append("append", "B").longValue());
        Assert.assertEquals("AB", stringCommands.get("append"));
    }

    @Test
    public void getrange() {
        stringCommands.set("getrange", "ABCDEFG");
        Assert.assertEquals("BCD", stringCommands.getrange("getrange", 1, 3));
        Assert.assertEquals("EFG", stringCommands.getrange("getrange", -3, -1));
        Assert.assertEquals("ABCDEFG", stringCommands.getrange("getrange", 0, -1));
    }

    @Test
    public void setrange() {
        Assert.assertEquals(3, stringCommands.setrange("setrange", 0, "ABC").longValue());
        Assert.assertEquals("ABC", stringCommands.get("setrange"));
        Assert.assertEquals(4, stringCommands.setrange("setrange", 1, "DEF").longValue());
        Assert.assertEquals("ADEF", stringCommands.get("setrange"));
    }

    @Test
    public void strlen() {
        Assert.assertEquals("OK", stringCommands.set("strlen", "value1"));
        Assert.assertEquals(6, stringCommands.strlen("strlen").longValue());
    }

    @After
    public void clean() {
        connection.sync().flushdb();
    }
}

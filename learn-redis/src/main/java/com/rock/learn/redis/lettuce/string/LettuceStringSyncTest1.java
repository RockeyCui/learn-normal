package com.rock.learn.redis.lettuce.string;

import io.lettuce.core.SetArgs;

import static java.lang.Thread.sleep;

/**
 * @author cuishilei
 * @date 2019/9/18
 */
public class LettuceStringSyncTest1 extends AbstractStringTest {

    public static void main(String[] args) throws InterruptedException {
        //set
        System.out.println("set");
        System.out.println(commands.set("rock", "rock"));
        //get
        System.out.println("get");
        System.out.println(commands.get("rock"));
        //del
        System.out.println("del");
        System.out.println(keyCommands.del("rock"));
        //del not exit
        System.out.println("del not exit");
        System.out.println(keyCommands.del("notExist"));
        //setnx
        System.out.println("setnx");
        System.out.println(commands.setnx("nx", "value1"));
        System.out.println(commands.setnx("nx", "value2"));
        //expect value1
        System.out.println(commands.get("nx"));
        //set xx
        //expect fail
        System.out.println("set xx");
        System.out.println(commands.set("xx", "xxx", new SetArgs().xx()));
        //getset
        commands.set("getset", "oldValue");
        System.out.println("getset");
        System.out.println(commands.getset("getset", "newValue"));
        //setex
        System.out.println("setex");
        commands.setex("rock", 2, "rock");
        sleep(3000);
        //expect null
        System.out.println(commands.get("rock"));

        connection.close();
        redisClient.shutdown();
    }
}

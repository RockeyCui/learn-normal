package com.rock.learn.redis.lettuce.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cuishilei
 * @date 2019/9/25
 */
public class HashTest1 extends AbstractHashTest {

    public static void main(String[] args) {
        String learnKey1 = "learnHash";
        String learnKey2 = "learnHashNotExit";
        keyCommands.del(learnKey1, learnKey2);
        //向 hash 添加（不存在 field ）或覆盖（存在 field ）一个 field value
        System.out.println(commands.hset(learnKey1, "name", "rock"));
        System.out.println(commands.hset(learnKey1, "sex", "1"));
        printHash(learnKey1);
        //向 hash 添加一个 field value，如果 field 存在则无效
        System.out.println(commands.hsetnx(learnKey1, "name", "rock1"));
        printHash(learnKey1);
        //向 hash 添加一个或多个 field value ，存在的 field 则覆盖
        Map<String, String> map = new HashMap<>();
        map.put("name", "rockey");
        map.put("age", "100");
        map.put("weight", "100");
        System.out.println(commands.hmset(learnKey1, map));
        printHash(learnKey1);
        //从 hash 删除一个或多个 field 返回删除 field 的个数（不包括不存在的 field ）
        System.out.println(commands.hdel(learnKey1, "age", "weight", "not"));
        printHash(learnKey1);
        //将 hash 中的 field value 进行 + increment 操作，如果 key 或 field 不存在则从 0 计算
        System.out.println(commands.hincrby(learnKey1, "incr", 10));
        printHash(learnKey1);
        //判断 hash 中是否存在 field
        System.out.println(commands.hexists(learnKey1, "name"));
        System.out.println(commands.hexists(learnKey1, "nameeee"));
        //返回 hash 中存储的所有 field
        System.out.println(commands.hkeys(learnKey1));
        //返回 hash 中存储的所有 value
        System.out.println(commands.hvals(learnKey1));
        //返回 hash 中存储的所有 field 的个数
        System.out.println(commands.hlen(learnKey1));
    }

    private static void printHash(String key) {
        System.out.println(commands.hgetall(key));
    }
}

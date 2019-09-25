package com.rock.learn.redis.lettuce.string;

/**
 * @author cuishilei
 * @date 2019/9/18
 */
public class LettuceStringSyncTest3 extends AbstractStringTest {

    public static void main(String[] args) {
        //追加，如果 key 为 null ，则写入，返回追加后的长度
        System.out.println(commands.append("append", "la"));
        System.out.println(commands.get("append"));

        commands.set("rock", "rock");
        //get range 获取范围内的 value 值，包括 start end
        //print ro
        System.out.println(commands.getrange("rock", 0, 1));
        //set range 设置索引位置到字符串结尾的 value 值
        commands.setrange("rock", 1, "abc");
        //print rabc
        System.out.println(commands.get("rock"));
        //strlen
        System.out.println(commands.strlen("rock"));
        commands.set("chinese", "你好呀");
        //expect 9
        System.out.println(commands.strlen("chinese"));

        connection.close();
        redisClient.shutdown();
    }
}

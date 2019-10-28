package com.rock.learn.redis.lettuce.string;

/**
 * @author cuishilei
 * @date 2019/9/18
 */
public class LettuceStringSyncTest2 extends AbstractStringTest {

    public static void main(String[] args) {
        //自增自减操作
        //当 redis 对一个空的 key 进行自增或自减时，默认值为 0 进行
        System.out.println(stringCommands.incr("incr"));
        System.out.println(stringCommands.decr("decr"));
        //当 redis 对一个非 num 的 key 进行自增或自减时，返回错误
        stringCommands.set("notNum", "ha");
        try {
            stringCommands.incr("notNum");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //+n -n 操作
        System.out.println(stringCommands.incrby("incr", 10));
        System.out.println(stringCommands.decrby("decr", 10));
        //+float
        System.out.println(stringCommands.incrbyfloat("float", 1.1));

        connection.close();
        redisClient.shutdown();
    }
}

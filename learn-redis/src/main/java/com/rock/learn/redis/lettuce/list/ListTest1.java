package com.rock.learn.redis.lettuce.list;

import java.util.Arrays;
import java.util.List;

/**
 * @author cuishilei
 * @date 2019/9/23
 */
public class ListTest1 extends AbstractListTest {

    public static void main(String[] args) {
        String learnKey1 = "learnList";
        String learnKey2 = "learnListNotExit";

        keyCommands.del(learnKey1, learnKey2);

        List<String> data = Arrays.asList("a", "b", "c", "d");
        //rpush
        resetKey(learnKey1, data);
        System.out.println(commands.rpush(learnKey1, "e", "f"));
        printKey(learnKey1);
        //lpush
        System.out.println(commands.lpush(learnKey1, "a0"));
        printKey(learnKey1);
        //rpop lpop
        System.out.println(commands.rpop(learnKey1));
        System.out.println(commands.lpop(learnKey1));
        printKey(learnKey1);
        //rpushx lpushx
        System.out.println(commands.rpushx(learnKey2, "a", "s", "d"));
        System.out.println(commands.lpushx(learnKey2, "a", "s", "d"));
        printKey(learnKey2);
        //ltrim key start stop 修剪链表，只保留索引在（包括）start stop 之间的元素
        resetKey(learnKey1, data);
        System.out.println(commands.ltrim(learnKey1, 0, 1));
        printKey(learnKey1);
        //ltrim start 超过链表长度，相当于剪了一块空的，key 就被删除了
        resetKey(learnKey1, data);
        System.out.println(commands.ltrim(learnKey1, data.size() + 1, data.size() + 2));
        printKey(learnKey1);
        //linsert key BEFORE|AFTER pivot value | 从头部开始寻找指定值为 pivot 的元素并在其前面后者后面插入新元素
        resetKey(learnKey1, data);
        commands.linsert(learnKey1, true, "c", "c1");
        commands.linsert(learnKey1, false, "c", "c2");
        //expect  a b c1 c c2 d
        printKey(learnKey1);
        //lset key index value  将链表索引为 index 的元素设置为新的 value，超出链表范围报错
        resetKey(learnKey1, data);
        commands.lset(learnKey1, 0, "a0");
        printKey(learnKey1);
        //lrem key count value  从头部（count>0）或从尾部（count<0）开始删除 count 个值等于 value 的元素，count=0 则删除全部
        resetKey(learnKey1, Arrays.asList("a", "b", "a", "a", "c", "a", "d"));
        commands.lrem(learnKey1, 1, "a");
        //expect b a a c a d
        printKey(learnKey1);
        commands.lrem(learnKey1, -1, "a");
        //expect b a a c d
        printKey(learnKey1);
        commands.lrem(learnKey1, 0, "a");
        //expect b c d
        printKey(learnKey1);

        resetKey(learnKey1, data);
        //lindex key index  返回链表中索引为 index 的元素，注意不是弹出
        System.out.println(commands.lindex(learnKey1, 0));
        //lrange key start stop  返回链表中索引在（包括）start stop 之间的元素，注意不是弹出
        System.out.println(commands.lrange(learnKey1, 0, 2));
        printKey(learnKey1);
    }

    private static void printKey(String key) {
        System.out.println(commands.lrange(key, 0, -1));
    }

    private static void resetKey(String key, List<String> data) {
        keyCommands.del(key);
        commands.rpush(key, data.toArray(new String[]{}));
    }
}

package com.rock.learn.redis.lettuce.bitmaps;

/**
 * @author cuishilei
 * @date 2019/9/19
 */
public class BitCountExample extends AbstractExample {

    public static void main(String[] args) {
        String str = "hello world";
        stringCommands.set("bitcount", str);
        for (int i = 0; i < str.length() * 8; i++) {
            //一个英文字符占一个字节（Byte），一个字节等于8位（bit）
            if (i % 8 == 0 && i != 0) {
                System.out.print(" ");
            }
            //获取偏移量位置的位值
            System.out.print(stringCommands.getbit("bitcount", i));
        }
        System.out.println();
        //统计所有的字节
        Long bitcount = stringCommands.bitcount("bitcount");
        System.out.println(bitcount);
        //统计前两个字节
        bitcount = stringCommands.bitcount("bitcount", 0, 1);
        System.out.println(bitcount);
    }
}

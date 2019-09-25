package com.rock.learn.redis.lettuce.bitmaps;

/**
 * @author cuishilei
 * @date 2019/9/19
 */
public class GetBitExample extends AbstractExample {

    public static void main(String[] args) {
        String str = "hello world";
        //获取字符串的二进制串
        String bitStr = getStringBitStr(str);
        System.out.println(bitStr);
        stringCommands.set("getbit", str);
        for (int i = 0; i < str.length() * 8; i++) {
            //一个英文字符占一个字节（Byte），一个字节等于8位（bit）
            if (i % 8 == 0 && i != 0) {
                System.out.print(" ");
            }
            //获取偏移量位置的位值
            System.out.print(stringCommands.getbit("getbit", i));
        }
    }
}

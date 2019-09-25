package com.rock.learn.redis.lettuce.bitmaps;

/**
 * @author cuishilei
 * @date 2019/9/19
 */
public class SetBitExample extends AbstractExample {

    public static void main(String[] args) {
        String str = "hello world";
        stringCommands.set("setbit", str);
        for (int i = 0; i < str.length() * 8; i++) {
            //一个英文字符占一个字节（Byte），一个字节等于8位（bit）
            if (i % 8 == 0 && i != 0) {
                System.out.print(" ");
            }
            //获取偏移量位置的位值
            System.out.print(stringCommands.getbit("setbit", i));
        }
        System.out.println();
        //设置第五位值为1
        stringCommands.setbit("setbit", 5, 1);
        for (int i = 0; i < str.length() * 8; i++) {
            //一个英文字符占一个字节（Byte），一个字节等于8位（bit）
            if (i % 8 == 0 && i != 0) {
                System.out.print(" ");
            }
            //获取偏移量位置的位值
            System.out.print(stringCommands.getbit("setbit", i));
        }
    }
}

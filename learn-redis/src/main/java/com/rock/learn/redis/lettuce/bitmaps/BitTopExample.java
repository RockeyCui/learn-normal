package com.rock.learn.redis.lettuce.bitmaps;

/**
 * @author cuishilei
 * @date 2019/9/19
 */
public class BitTopExample extends AbstractExample {

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "world";
        stringCommands.set("bittop1", str1);
        stringCommands.set("bittop2", str2);
        printBitStr("bittop1", str1.length() * 8);
        printBitStr("bittop2", str2.length() * 8);

        stringCommands.bitopAnd("bittopand", "bittop1", "bittop2");
        System.out.println("and");
        printBitStr("bittopand", str2.length() * 8);

        stringCommands.bitopOr("bittopor", "bittop1", "bittop2");
        System.out.println("or");
        printBitStr("bittopor", str2.length() * 8);

        stringCommands.bitopXor("bittopxor", "bittop1", "bittop2");
        System.out.println("xor");
        printBitStr("bittopxor", str2.length() * 8);

        stringCommands.bitopNot("bittopnot", "bittop1");
        System.out.println("not");
        printBitStr("bittopnot", str2.length() * 8);
    }

    private static void printBitStr(String key, int length) {
        for (int i = 0; i < length; i++) {
            //一个英文字符占一个字节（Byte），一个字节等于8位（bit）
            if (i % 8 == 0 && i != 0) {
                System.out.print(" ");
            }
            //获取偏移量位置的位值
            System.out.print(stringCommands.getbit(key, i));
        }
        System.out.println();
    }
}

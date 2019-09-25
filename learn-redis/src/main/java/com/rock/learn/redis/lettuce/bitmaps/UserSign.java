package com.rock.learn.redis.lettuce.bitmaps;

import com.rock.learn.util.DateTimeUtil;

import java.util.*;

/**
 * @author cuishilei
 * @date 2019/9/19
 */
public class UserSign extends AbstractExample {
    private static List<Integer> users = new ArrayList<>();
    private static Random random = new Random();

    static {
        for (int i = 0; i < 100000; i++) {
            users.add(i);
        }
    }

    public static void main(String[] args) {
        String today = DateTimeUtil.getNowDateStr(DateTimeUtil.DATE_FORMAT_YYMMDD);
        String todayKey = "SIGN" + today;
        System.out.println("今天是" + today);
        //模拟用户签到，随机取出几个用户
        int total = random.nextInt(users.size() - 1);
        List<Integer> randomUsers = getUsers(total);
        System.out.println("签到数 " + randomUsers.size());
        for (Integer randomUser : randomUsers) {
            stringCommands.setbit(todayKey, randomUser, 1);
        }
        long begin, end;
        //判断用户是否签到
        begin = System.currentTimeMillis();
        int i = random.nextInt(UserSign.users.size() - 1);
        Integer test = UserSign.users.get(i);
        Long getbit = stringCommands.getbit(todayKey, test);
        System.out.println("用户 " + test + " 签到状态 " + getbit);
        end = System.currentTimeMillis();
        System.out.println("time " + (end - begin) + " ms");
        //统计签到数
        begin = System.currentTimeMillis();
        Long bitcount = stringCommands.bitcount(todayKey);
        System.out.println("签到数 " + bitcount + " 预测数 " + total);
        end = System.currentTimeMillis();
        System.out.println("time " + (end - begin) + " ms");
    }

    private static List<Integer> getUsers(int size) {
        Set<Integer> set = new HashSet<>();
        while (set.size() != size) {
            int nextInt = random.nextInt(users.size() - 1);
            set.add(users.get(nextInt));
        }
        return new ArrayList<>(set);
    }
}

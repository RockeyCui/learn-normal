package com.rock.learn.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RockeyCui
 */
public class SelectionSort extends AbstractSort {

    @Override
    public int[] doSort(int[] array) {
        //数组长度,选择次数
        int size = array.length;
        //开始选择
        for (int i = 0; i < size; i++) {
            //从未排序元素第一个开始，i = 0 时整个数组都是未排序元素
            int tmpIndex = i;
            for (int j = i; j < size; j++) {
                //对比选出最小值索引
                if (array[j] < array[tmpIndex]) {
                    tmpIndex = j;
                }
            }
            //新的最小值
            int tmp = array[tmpIndex];
            //最小值放到已排序的序列的末尾
            array[tmpIndex] = array[i];
            array[i] = tmp;
            //打印一下过程与算法无关
            List<Integer> list = new ArrayList<>();
            for (int i1 : array) {
                list.add(i1);
            }
            System.out.println(list.subList(0, i + 1).toString() + " " + list.subList(i + 1, list.size()).toString());
        }
        return array;
    }
}
package com.rock.learn.sort;

/**
 * @author cuishilei
 * @date 2019/9/16
 */
public class InsertionSort extends AbstractSort {
    @Override
    int[] doSort(int[] array) {
        //向前插入，从1开始与前面元素的比较插入
        for (int i = 1; i < array.length; i++) {
            //当前未排序序列的第一个元素
            int temp = array[i];
            //未排序序列前一个元素的索引，也是已排序序列的最后元素索引
            int preIndex = i - 1;
            //从后向前遍历已排序序列，如果 temp 小于遍历的元素，则遍历的元素后移一位
            while (preIndex >= 0 && temp < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            //所有大于 temp 的元素后移一位后，temp填充在此位
            array[preIndex + 1] = temp;

            //打印一下过程与算法无关
            System.out.println(printFun(array, i, preIndex + 1));
        }
        return array;
    }
}

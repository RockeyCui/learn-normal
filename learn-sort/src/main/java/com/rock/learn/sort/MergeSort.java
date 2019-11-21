package com.rock.learn.sort;

import java.util.Arrays;

/**
 * @author cuishilei
 * @date 2019/9/18
 */
public class MergeSort extends AbstractSort {
    @Override
    int[] doSort(int[] array) {
        //如果数组只剩下一个元素直接返回
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        //拆分
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        //递归对拆分的数组继续进行归并排序
        return merge(doSort(left), doSort(right));
    }

    /**
     * 由于递归结束条件为元素为1，所以调用栈是从一个元素向上拼成有序数组，
     * 每次传入的 left 和 right 都是有序的
     */
    public int[] merge(int[] left, int[] right) {
        int size = left.length + right.length;
        //merge 结果
        int[] merge = new int[size];
        //left 遍历索引
        int l = 0;
        //right 遍历索引
        int r = 0;
        //merge 索引
        int m = 0;
        //遍历两个数组，知道某一个数组遍历完毕结束
        while (l < left.length && r < right.length) {
            System.out.println("left    " + printFun(left, l));
            System.out.println("right   " + printFun(right, r));
            //判断大小并插入 merge
            if (left[l] <= right[r]) {
                merge[m++] = left[l++];
            } else {
                merge[m++] = right[r++];
            }
            System.out.println("merge   " + printFun(merge, m - 1));
        }
        //由于是有序的，如果 left 剩下元素，直接插入 merge 后面
        while (l < left.length) {
            merge[m++] = left[l++];
        }
        //由于是有序的，如果 right 剩下元素，直接插入 merge 后面
        while (r < right.length) {
            merge[m++] = right[r++];
        }
        System.out.println("merge   " + printFun(merge, m, m + 1));
        return merge;
    }
}

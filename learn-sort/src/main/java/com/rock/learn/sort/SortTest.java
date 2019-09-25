package com.rock.learn.sort;

import java.util.Arrays;

/**
 * @author cuishilei
 * @date 2019/9/12
 */
public class SortTest {
    public static void main(String[] args) {

        int[] ints = new int[]{9, 6, 0, 2, 3};

        Sort sort;
        sort = new SelectionSort();
        //sort = new BubbleSort();
        sort = new InsertionSort();
        sort = new ShellSort();
        sort = new MergeSort();
        System.out.println("未排序 " + Arrays.toString(ints));
        System.out.println("已排序 " + Arrays.toString(sort.sort(ints)));
    }
}

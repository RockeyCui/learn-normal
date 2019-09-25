package com.rock.learn.sort;

/**
 * @author cuishilei
 * @date 2019/9/16
 */
public class ShellSort extends AbstractSort {
    @Override
    int[] doSort(int[] array) {
        int len = array.length;
        //步长
        int step = len / 2;
        //临时未排序元素
        int temp;
        //步长缩小至为1，进行最后的插入排序结束
        while (step > 0) {
            //步长后的未排序元素与步长前的已排序元素进行插入排序
            for (int i = step; i < len; i++) {
                temp = array[i];
                int preIndex = i - step;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + step] = array[preIndex];
                    preIndex -= step;
                }
                array[preIndex + step] = temp;
                System.out.println(printFun(array, i, preIndex + step));
            }
            //步长缩小
            step /= 2;
        }
        return array;
    }
}

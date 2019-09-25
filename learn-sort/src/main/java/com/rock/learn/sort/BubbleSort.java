package com.rock.learn.sort;

/**
 * @author cuishilei
 * @date 2019/9/12
 */
public class BubbleSort extends AbstractSort {

    @Override
    int[] doSort(int[] array) {
        //数组长度
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            //冒泡排序每次比较如果没有发生排序，说明已经排序好了，可以直接跳过
            boolean isChange = false;
            //每冒一次泡，对比次数就要减少一次
            for (int j = 0; j < arraySize - 1 - i; j++) {
                //打印一下过程与算法无关
                System.out.println("冒泡前 " + printFun(array, j, j + 1));
                //交换
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isChange = true;
                    //打印一下过程与算法无关
                    System.out.println("冒泡后 " + printFun(array, j, j + 1));
                }
            }
            if (!isChange) {
                break;
            }
        }
        return array;
    }
}

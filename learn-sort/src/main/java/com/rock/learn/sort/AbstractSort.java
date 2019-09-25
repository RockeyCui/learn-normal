package com.rock.learn.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cuishilei
 * @date 2019/9/12
 */
public abstract class AbstractSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        if (array == null) {
            return null;
        }
        //数组长度
        int arraySize = array.length;
        if (arraySize == 0) {
            return array;
        }
        return doSort(array);
    }

    /**
     * 排序方法
     *
     * @param array 待排序数组
     * @return int[] 已排序数组
     * @author cuishilei
     * @date 2019/9/12
     */
    abstract int[] doSort(int[] array);

    protected String printFun(int[] array, int... indexes) {
        List<Integer> list = new ArrayList<>();
        for (int index : indexes) {
            list.add(index);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (list.contains(i)) {
                sb.append("[").append(array[i]).append("]");
            } else {
                sb.append(array[i]);
            }
            sb.append(" ");
        }
        return sb.toString();
    }
}

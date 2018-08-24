package com.lpg.sort;

/**
 * 快速排序：Java
 *????????????????/
 * @author skywang
 * @date 2014/03/11
 */

public class QuickSort {

    /*
     * 快速排序
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     l -- 数组的左边界(例如，从起始位置开始排序，则l=0)
     *     r -- 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
     */
    public static void quickSort(int[] arr, int left, int right) {

        if (left < right) {
            int i,j,x;

            i = left;
            j = right;
            x = arr[i];
            while (i < j) {
                while(i < j && arr[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if(i < j)
                    arr[i++] = arr[j];
                while(i < j && arr[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if(i < j)
                    arr[j--] = arr[i];
            }
            arr[i] = x;
            quickSort(arr, left, i-1); /* 递归调用 */
            quickSort(arr, i+1, right); /* 递归调用 */
        }
    }

    public static void main(String[] args) {
        int i;
        int a[] = {30,40,60,10,20,50};

        System.out.printf("before sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        quickSort(a, 0, a.length-1);

        System.out.printf("after  sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");
    }
}

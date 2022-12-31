import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

public class SortStudy {
    static int[] sorted_arr = {1,3,5,8,9};
    static int[] unsorted_arr = {5,3,1,6,8,2,9,4};

    /**
     * 冒泡排序
     */
    public static void bubble(){
        for(int i=1;i< unsorted_arr.length;i++){
            for(int j=0;j< unsorted_arr.length-1;j++){
                if(unsorted_arr[j]> unsorted_arr[j+1]){
                    int temp = unsorted_arr[j];
                    unsorted_arr[j] = unsorted_arr[j+1];
                    unsorted_arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(unsorted_arr));
    }

    private static int doublePointerSwap(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int leftPoint = startIndex;
        int rightPoint = endIndex;

        while (leftPoint < rightPoint) {
            // 从右向左找出比pivot小的数据
            while (leftPoint < rightPoint
                    && arr[rightPoint] > pivot) {
                rightPoint--;
            }
            // 从左向右找出比pivot大的数据
            while (leftPoint < rightPoint
                    && arr[leftPoint] <= pivot) {
                leftPoint++;
            }
            // 没有过界则交换
            if (leftPoint < rightPoint) {
                int temp = arr[leftPoint];
                arr[leftPoint] = arr[rightPoint];
                arr[rightPoint] = temp;
            }
        }
        // 最终将分界值与当前指针数据交换
        arr[startIndex] = arr[rightPoint];
        arr[rightPoint] = pivot;
        // 返回分界值所在下标
        return rightPoint;
    }

    /**
     * 入口函数（递归方法），算法的调用从这里开始。
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        // 核心算法部分：分别介绍 双边指针（交换法），双边指针（挖坑法），单边指针
        int pivotIndex = doublePointerSwap(arr, startIndex, endIndex);

        // 用分界值下标区分出左右区间，进行递归调用
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    public static void main(String[] args) {
        //bubble();
        quickSort(unsorted_arr,0,7);
        System.out.println(JSONObject.toJSON(unsorted_arr));
    }
}

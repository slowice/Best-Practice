package leecode;

/**
 * 寻找中位数
 * 此题要求时间复杂度为O(long(m+n))，暗示要采用二分法，题目又为hard难度，分两天完成
 */
public class FindMedianSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0d;
        int[] shortArr;
        int[] longArr;
        int length1 = nums1.length;
        int length2 = nums2.length;
        shortArr = length1<=length2?nums1:nums2;
        longArr = length1<=length2?nums2:nums1;
        for(int i=0;i<longArr.length;i++){
            for (int j=0;j<shortArr.length;j++){

            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};

        System.out.println(findMedianSortedArrays(nums1,nums2));
    }
}

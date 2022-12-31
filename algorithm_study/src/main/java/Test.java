public class Test {
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            for(int i=0;i<nums.length-1;i++){
                for(int j=i+1; j< nums.length-1;j++){
                    if(nums[i] + nums[j] == target){
                        return new int[]{i,j};
                    }
                }
            }
            return null;
        }
    }

    public static void test(String[] args) {
        Solution s = new Solution();
        s.twoSum(new int[]{3,2,4},6);
    }

    public static void main(String[] args) {
        test(args);
    }
}

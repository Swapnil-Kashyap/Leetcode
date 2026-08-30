class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
       int n = nums.length;
       int[] answer = new int[n];
       int xor =0;
       for(int num:nums){
        xor^= num;
       }
       int max = (1 << maximumBit) -1;
       for(int i=0;i<n;i++){
         answer[i] = xor^max ;
         xor^= nums[n-1-i];
       }
       return answer;
    }
}
class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] present = new boolean[nums.length + 2];
        for (int num : nums) {
            if (num > 0 && num % k == 0) {
                int index = num / k;
                if (index < present.length) {
                    present[index] = true;
                }
            }
        }
        for (int i = 1; i < present.length; i++) {
            if (!present[i]) {
                return i * k;
            }
        }
        return -1;
    }
}
class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] dp = new long[k];
        long[] result = new long[k];

        for (int num : nums) {

            long[] next = new long[k];

            int value = num % k;

            // Start a new subarray
            next[value]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newR = (int)((long) r * value % k);
                    next[newR] += dp[r];
                }
            }

            dp = next;

            // Add subarrays ending at current position
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }

        return result;
    }
}
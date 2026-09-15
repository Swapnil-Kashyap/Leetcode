class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        boolean[][] pal = new boolean[n][n];

        for (int len = 1; len <= n; len++) {
            for (int start = 0; start + len <= n; start++) {
                int end = start + len - 1;

                if (s.charAt(start) == s.charAt(end) &&
                    (len <= 2 || pal[start + 1][end - 1])) {
                    pal[start][end] = true;
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];

            for (int start = 0; start <= i - k; start++) {
                int end = i - 1;

                if (pal[start][end]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
        }

        return dp[n];
    }
}
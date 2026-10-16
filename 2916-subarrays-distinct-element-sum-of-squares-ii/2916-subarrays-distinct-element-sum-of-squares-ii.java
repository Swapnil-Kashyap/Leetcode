class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] sum = new long[4 * n];
        long[] sq = new long[4 * n];
        long[] lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> last = new java.util.HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);
            update(1, 0, n - 1, prev + 1, i, sum, sq, lazy);
            ans = (ans + sq[1]) % MOD;
            last.put(nums[i], i);
        }

        return (int) ans;
    }

    void update(int node, int l, int r, int ql, int qr,
                long[] sum, long[] sq, long[] lazy) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            sq[node] = (sq[node] + 2 * sum[node] + (r - l + 1)) % MOD;
            sum[node] = (sum[node] + (r - l + 1)) % MOD;
            lazy[node]++;
            return;
        }

        push(node, l, r, sum, sq, lazy);

        int mid = l + (r - l) / 2;
        update(node * 2, l, mid, ql, qr, sum, sq, lazy);
        update(node * 2 + 1, mid + 1, r, ql, qr, sum, sq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    void push(int node, int l, int r, long[] sum, long[] sq, long[] lazy) {
        if (lazy[node] == 0 || l == r) return;

        int mid = l + (r - l) / 2;
        apply(node * 2, l, mid, lazy[node], sum, sq, lazy);
        apply(node * 2 + 1, mid + 1, r, lazy[node], sum, sq, lazy);

        lazy[node] = 0;
    }

    void apply(int node, int l, int r, long v,
               long[] sum, long[] sq, long[] lazy) {
        long len = r - l + 1L;
        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + v % MOD * v % MOD * len) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }
}
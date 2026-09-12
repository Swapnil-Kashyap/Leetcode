import java.util.*;

class Solution {

    static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> input) {

        int n = input.size();

        int[][] intervals = new int[n][4];

        for (int i = 0; i < n; i++) {
            intervals[i][0] = input.get(i).get(0);
            intervals[i][1] = input.get(i).get(1);
            intervals[i][2] = input.get(i).get(2);
            intervals[i][3] = i;
        }

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int low = i + 1;
            int high = n;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (intervals[mid][0] > intervals[i][1]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            next[i] = low;
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {

            dp[i][0] = new State(0, new int[0]);

            for (int k = 1; k <= 4; k++) {

                State skip = dp[i + 1][k];

                State after = dp[next[i]][k - 1];

                int[] takenIndices = new int[after.indices.length + 1];

                takenIndices[0] = intervals[i][3];

                for (int j = 0; j < after.indices.length; j++) {
                    takenIndices[j + 1] = after.indices[j];
                }

                Arrays.sort(takenIndices);

                State take = new State(
                    intervals[i][2] + after.score,
                    takenIndices
                );

                dp[i][k] = better(take, skip);
            }
        }

        return dp[0][4].indices;
    }

    private State better(State a, State b) {

        if (a.score > b.score) {
            return a;
        }

        if (b.score > a.score) {
            return b;
        }

        int len = Math.min(a.indices.length, b.indices.length);

        for (int i = 0; i < len; i++) {
            if (a.indices[i] < b.indices[i]) {
                return a;
            }

            if (a.indices[i] > b.indices[i]) {
                return b;
            }
        }

        return a.indices.length <= b.indices.length ? a : b;
    }
}
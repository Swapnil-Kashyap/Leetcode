import java.util.*;

class Solution {

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        if (n == 1) return 1;

        int[] sa = buildSuffixArray(nums);
        int[] lcp = buildLCP(nums, sa);

        int answer = n;

        for (int r = 0; r < n; r++) {
            int maxLCP = lcp[r];

            if (r + 1 < n) {
                maxLCP = Math.max(maxLCP, lcp[r + 1]);
            }

            int uniqueLength = maxLCP + 1;
            int start = sa[r];

            if (uniqueLength <= n - start) {
                answer = Math.min(answer, uniqueLength);
            }
        }

        return answer;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;

        long[] sorted = new long[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = ((long) nums[i] << 32) | (i & 0xffffffffL);
        }

        Arrays.sort(sorted);

        int[] sa = new int[n];
        int[] rank = new int[n];

        int classes = 0;

        for (int i = 0; i < n; i++) {
            sa[i] = (int) sorted[i];

            if (i == 0 || nums[sa[i]] != nums[sa[i - 1]]) {
                classes++;
            }

            rank[sa[i]] = classes - 1;
        }

        int[] temp = new int[n];
        int[] newRank = new int[n];

        for (int k = 1; k < n && classes < n; k <<= 1) {

            countingSort(sa, temp, rank, k, n, classes);
            int[] swap = sa;
            sa = temp;
            temp = swap;

            countingSort(sa, temp, rank, 0, n, classes);
            swap = sa;
            sa = temp;
            temp = swap;

            int newClasses = 1;
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int current = sa[i];
                int previous = sa[i - 1];

                int currentSecond = current + k < n
                        ? rank[current + k] : -1;

                int previousSecond = previous + k < n
                        ? rank[previous + k] : -1;

                if (rank[current] != rank[previous]
                        || currentSecond != previousSecond) {
                    newClasses++;
                }

                newRank[current] = newClasses - 1;
            }

            classes = newClasses;

            swap = rank;
            rank = newRank;
            newRank = swap;

            if (k > n / 2) break;
        }

        return sa;
    }

    private void countingSort(
            int[] sa,
            int[] output,
            int[] rank,
            int offset,
            int n,
            int classes) {

        int[] count = new int[classes + 1];

        for (int pos : sa) {
            int key = pos + offset < n
                    ? rank[pos + offset] + 1
                    : 0;

            count[key]++;
        }

        for (int i = 1; i <= classes; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int pos = sa[i];

            int key = pos + offset < n
                    ? rank[pos + offset] + 1
                    : 0;

            output[--count[key]] = pos;
        }
    }

    private int[] buildLCP(int[] nums, int[] sa) {
        int n = nums.length;

        int[] inverse = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            inverse[sa[i]] = i;
        }

        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = inverse[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n
                    && nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        return lcp;
    }
}
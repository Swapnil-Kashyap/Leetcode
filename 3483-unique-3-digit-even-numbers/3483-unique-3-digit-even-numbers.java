class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        for (int i = 100; i <= 999; i++) {
            if (i % 2 != 0) {
                continue;
            }

            int a = i / 100;
            int b = (i / 10) % 10;
            int c = i % 10;

            int[] used = new int[10];
            used[a]++;
            used[b]++;
            used[c]++;

            boolean possible = true;

            for (int d = 0; d < 10; d++) {
                if (used[d] > freq[d]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                count++;
            }
        }

        return count;
    }
}
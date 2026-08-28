class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                if (mid != 0) {
                    return "";
                }
                mid = (char) ('a' + i);
                cnt[i]--;
            }
        }

        int half = n / 2;

        for (int i = 0; i < half; i++) {
            cnt[target.charAt(i) - 'a'] -= 2;
        }

        boolean valid = true;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] < 0) {
                valid = false;
                break;
            }
        }

        if (valid) {
            String left = target.substring(0, half);

            StringBuilder right = new StringBuilder();

            if (n % 2 == 1) {
                right.append(mid);
            }

            right.append(new StringBuilder(left).reverse());

            String candidate = left + right.toString();

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        for (int i = half - 1; i >= 0; i--) {

            cnt[target.charAt(i) - 'a'] += 2;

            valid = true;

            for (int x = 0; x < 26; x++) {
                if (cnt[x] < 0) {
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                continue;
            }

            int current = target.charAt(i) - 'a';

            for (int j = current + 1; j < 26; j++) {

                if (cnt[j] < 2) {
                    continue;
                }

                cnt[j] -= 2;

                StringBuilder left = new StringBuilder();

                left.append(target, 0, i);
                left.append((char) ('a' + j));

                for (int c = 0; c < 26; c++) {
                    for (int x = 0; x < cnt[c] / 2; x++) {
                        left.append((char) ('a' + c));
                    }
                }

                StringBuilder answer = new StringBuilder();

                answer.append(left);

                if (n % 2 == 1) {
                    answer.append(mid);
                }

                answer.append(new StringBuilder(left).reverse());

                return answer.toString();
            }
        }

        return "";
    }
}
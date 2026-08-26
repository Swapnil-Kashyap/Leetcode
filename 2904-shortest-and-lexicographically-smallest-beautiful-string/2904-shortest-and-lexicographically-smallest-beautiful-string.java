class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int ones = 0;
        String answer = "";
        int minLength = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }
            if(ones == k) {
                while(s.charAt(left) == '0') {
                    left++;
                }
                int length = right - left + 1;
                String current = s.substring(left, right + 1);
                if(length < minLength) {
                    minLength = length;
                    answer = current;
                } else if (length == minLength &&
                           current.compareTo(answer) < 0) {
                    answer =current;
                }
                left++;
                ones--;
            }
        }
        return answer;
    }
}
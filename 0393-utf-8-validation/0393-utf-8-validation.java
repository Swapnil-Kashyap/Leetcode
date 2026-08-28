class Solution {
    public boolean validUtf8(int[] data) {
        int remaining = 0;
        for (int num : data) {
            num = num & 255;
            if (remaining == 0) {
                if ((num >> 7) == 0) {
                    remaining = 0;
                }
                else if((num >> 5) == 6) {
                    remaining = 1;
                }
                else if((num >> 4) == 14) {
                    remaining = 2;
                }
                else if((num >> 3) == 30) {
                    remaining = 3;
                }
                else{
                    return false;
                }
            } else{
                if((num >> 6) != 2) {
                    return false;
                }
               remaining--;
            }
        }
        return remaining ==0;
    }
}
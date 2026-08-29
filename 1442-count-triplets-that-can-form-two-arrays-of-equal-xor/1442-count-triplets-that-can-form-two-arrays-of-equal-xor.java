class Solution {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int count = 0;
        int xor = 0;
        java.util.HashMap<Integer, Integer> freq = new java.util.HashMap<>();
         java.util.HashMap<Integer, Integer> sum = new java.util.HashMap<>();
        freq.put(0,1);
         sum.put(0, 0);
        for(int k=0; k<n; k++){
            xor^= arr[k];
         if (freq.containsKey(xor)){
            count += freq.get(xor) * k - sum.get(xor);
          }
          freq.put(xor, freq.getOrDefault(xor, 0) + 1);
            sum.put(xor, sum.getOrDefault(xor, 0) + k + 1);
        }
        return count;
    }
}
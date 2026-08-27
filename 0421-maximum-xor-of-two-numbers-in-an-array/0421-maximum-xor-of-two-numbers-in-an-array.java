class Solution {
    public int findMaximumXOR(int[] nums) {
        int maxXor = 0;
        int mask = 0;
        for(int i = 31;i >=0;i--){
            mask |=(1<<i);
             HashSet<Integer> set = new HashSet<>();
             for(int num :nums){
                set.add(num & mask);
             }
             int candidate = maxXor | (1 << i);
             boolean found = false;
             for(int prefix : set){
                if((set.contains(prefix ^ candidate))){
                    found = true;
                    break;
                }
             }
            if(found){
                maxXor = candidate;
            }
        }
        return maxXor;
    }
}
class Solution {
    static void solve(int[] nums,int index,List<Integer> output,List<List<Integer>>ans){
        if(index >= nums.length){
            ans.add(new ArrayList<>(output));
            return;
        }
        int currValue = nums[index];
        output.add(currValue);
        solve(nums,index+1,output,ans);
        output.remove(output.size()-1);
        solve(nums, index+1,output, ans);
    }
    public List<List<Integer>> subsets(int[] nums) {
       List<List<Integer>>ans = new ArrayList<>();
       List<Integer> output = new ArrayList<>();
       int index = 0;
       solve(nums, 0, output, ans);

       return ans ;
    }
}
class Solution {
    static void solve(int[] candidate,int target, int index, List<List<Integer>> ans,List<Integer> output){
        if(target == 0){
            ans.add(new ArrayList(output));
            return;
        }
        if(index >= candidate.length){
            return;
        }
        if(target < 0){
            return;
        }
        output.add(candidate[index]);
        solve(candidate,target-candidate[index],index ,ans,output);
        output.remove(output.size()-1);
        solve(candidate,target,index+1,ans,output);

    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        int index = 0;
        solve(candidates,target,index,ans,output);
        return ans;
    }
}
class Solution {
    static void solve(int[] candidates, int target, int index, List<List<Integer>> ans, List<Integer> output) {
        if (target == 0) {
            ans.add(new ArrayList<>(output));
            return;
        }
        if (index >= candidates.length || target < 0) {
            return;
        }
        output.add(candidates[index]);
        solve(candidates, target - candidates[index], index + 1, ans, output);
        output.remove(output.size() - 1);
        while (index + 1 < candidates.length && candidates[index] == candidates[index + 1]) {
            index++;
        }
        solve(candidates, target, index + 1, ans, output);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        solve(candidates, target, 0, ans, output);
        return ans;
    }
}
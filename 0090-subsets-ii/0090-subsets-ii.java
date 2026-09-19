class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        solve(nums, 0, new ArrayList<>(), ans);
        return ans;
    }
    static void solve(int[] nums, int index, List<Integer> output, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(output));
            return;
        }
        output.add(nums[index]);
        solve(nums, index + 1, output, ans);
        output.remove(output.size() - 1);
        int next = index + 1;
        while (next < nums.length && nums[next] == nums[index]) {
            next++;
        }

        solve(nums, next, output, ans);
    }
}
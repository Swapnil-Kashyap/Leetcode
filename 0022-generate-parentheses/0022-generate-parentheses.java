import java.util.*;

class Solution {
    void solve(int n,int open,int close,String output,List<String> ans){
        if(output.length()==2*n){
            ans.add(output);
            return;
        }

        if(open<n){
            solve(n,open+1,close,output+"(",ans);
        }

        if(close<open){
            solve(n,open,close+1,output+")",ans);
        }
    }

    public List<String> generateParenthesis(int n){
        List<String> ans=new ArrayList<>();
        solve(n,0,0,"",ans);
        return ans;
    }
}
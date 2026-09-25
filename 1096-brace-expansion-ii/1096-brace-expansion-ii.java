import java.util.*;

class Solution {

    String s;
    int pos;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        pos = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Handles union: A,B
    Set<String> parseExpression() {

        Set<String> result = parseTerm();

        while (pos < s.length() && s.charAt(pos) == ',') {
            pos++;

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation: AB
    Set<String> parseTerm() {

        Set<String> result = new HashSet<>();
        result.add("");

        while (pos < s.length()
                && s.charAt(pos) != '}'
                && s.charAt(pos) != ',') {

            Set<String> next = parseFactor();

            Set<String> temp = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    // Handles a letter or {...}
    Set<String> parseFactor() {

        if (s.charAt(pos) == '{') {

            pos++;

            Set<String> result = parseExpression();

            pos++; // skip '}'

            return result;
        }

        Set<String> result = new HashSet<>();

        result.add(String.valueOf(s.charAt(pos)));

        pos++;

        return result;
    }
}
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        
        char[] current = new char[2 * n];
        
        for (int i = 0; i < n; i++) {
            current[i] = '(';
        }
        for (int i = n; i < 2 * n; i++) {
            current[i] = ')';
        }
        
        while (true) {
            result.add(new String(current));
            
            int open = 0;
            int close = 0;
            int i = 2 * n - 1;
            
            while (i >= 0) {
                if (current[i] == ')') {
                    close++;
                } else {
                    open++;
                    if (close > open) {
                        break;
                    }
                }
                i--;
            }
            
            if (i < 0) {
                break;
            }
            
            current[i] = ')';
            
            for (int j = 1; j <= open; j++) {
                current[i + j] = '(';
            }
            for (int j = 1; j <= close - 1; j++) {
                current[i + open + j] = ')';
            }
        }
        
        return result;
    }
}

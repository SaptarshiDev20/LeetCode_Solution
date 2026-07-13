import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        
        for (int length = 2; length <= 9; length++) {
            for (int startDigit = 1; startDigit <= 10 - length; startDigit++) {
                int num = startDigit;
                int nextDigit = startDigit + 1;
                
                for (int j = 1; j < length; j++) {
                    num = num * 10 + nextDigit;
                    nextDigit++;
                }
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}

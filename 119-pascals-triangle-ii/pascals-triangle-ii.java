import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        
        long currentVal = 1; 
        row.add((int) currentVal);
        
        for (int i = 1; i <= rowIndex; i++) {
            currentVal = currentVal * (rowIndex - i + 1) / i;
            row.add((int) currentVal);
        }
        
        return row;
    }
}

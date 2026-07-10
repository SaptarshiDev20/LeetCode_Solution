import java.util.HashMap;
import java.util.Map;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;

            if(map.containsKey(y)) {
                return new int[] {map.get(y), i};
            }
            map.put(x, i);
        }
        return new int[] {};
    }
}

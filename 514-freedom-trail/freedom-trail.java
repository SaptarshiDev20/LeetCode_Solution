import java.util.ArrayList;
import java.util.List;

class Solution {
    public int findRotateSteps(String ring, String key) {
        int rLen = ring.length(), kLen = key.length();
        
        int[][] pos = new int[26][];
        int[] counts = new int[26];
        char[] ringChars = ring.toCharArray();
        char[] keyChars = key.toCharArray();
        
        for (char c : ringChars) {
            counts[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                pos[i] = new int[counts[i]];
            }
        }
        
        int[] filled = new int[26];
        for (int i = 0; i < rLen; i++) {
            int idx = ringChars[i] - 'a';
            pos[idx][filled[idx]++] = i;
        }

        int[] prevPositions = pos[keyChars[0] - 'a'];
        int[] prevDp = new int[prevPositions.length];
        for (int i = 0; i < prevPositions.length; i++) {
            int p = prevPositions[i];
            prevDp[i] = Math.min(p, rLen - p) + 1;
        }

        for (int i = 1; i < kLen; i++) {
            int[] currPositions = pos[keyChars[i] - 'a'];
            int[] currDp = new int[currPositions.length];
            
            for (int cIdx = 0; cIdx < currPositions.length; cIdx++) {
                int curr = currPositions[cIdx];
                int minSteps = Integer.MAX_VALUE;
                for (int pIdx = 0; pIdx < prevPositions.length; pIdx++) {
                    int prev = prevPositions[pIdx];
                    int diff = curr > prev ? curr - prev : prev - curr;
                    int steps = diff < rLen - diff ? diff : rLen - diff;
                    int val = prevDp[pIdx] + steps;
                    if (val < minSteps) {
                        minSteps = val;
                    }
                }
                currDp[cIdx] = minSteps + 1;
            }
            prevPositions = currPositions;
            prevDp = currDp;
        }

        int minTotal = Integer.MAX_VALUE;
        for (int val : prevDp) {
            if (val < minTotal) {
                minTotal = val;
            }
        }

        return minTotal;
    }
}

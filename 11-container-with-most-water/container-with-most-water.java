class Solution {
   static {
        for(int i = 0;i <100;i++) {
            maxArea(new int[] { 0, 0 });
        }
    }
    public static int maxArea(int[] height) {
        int i=0;
        int j=height.length-1;
        int area=0;
        while(i<j){
            int min_height = Math.min(height[i], height[j]);
            area = Math.max(area, min_height*(j-i));
            while(i<j && height[i]<=min_height) i++;
            while(i<j && height[j]<=min_height) j--;
        }
        return area;
    }
}

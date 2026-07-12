class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n=arr.length;
        int temp[]=new int[n];
        for(int i=0;i<n;i++){
            temp[i]=arr[i];
        }
        Arrays.sort(temp);
        HashMap<Integer,Integer> rm=new HashMap<>();
        int rank=1;
        for(int num:temp){
            if(!rm.containsKey(num)){
                rm.put(num,rank++);
            }
        }
        for(int i=0;i<n;i++){
            temp[i]=rm.get(arr[i]);
        }
        return temp;
    }
}

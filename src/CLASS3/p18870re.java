package CLASS3;

import java.io.*;
import java.util.*;

public class p18870re {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int[] arr_sorted = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr_sorted[i] = arr[i][0];
        }

        //arr : 2 4 -10 4 -9
        //compressed: 2 3 0 3 1

        //sort: -10 -9 2 4 4
        Arrays.sort(arr_sorted);

        HashMap<Integer, Integer> numidx = new HashMap<>();
        int idx = 0;
        for(int i=0; i<N; i++){
            if( numidx.getOrDefault(arr_sorted[i], -1) == -1){
                numidx.put(arr_sorted[i], idx);
                idx++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append( numidx.get(arr[i][0]) ).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());



    }

}



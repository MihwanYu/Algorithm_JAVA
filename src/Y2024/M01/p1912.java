package Y2024.M01;

import java.io.*;
import java.util.*;

public class p1912 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] accum = new int[N];
        int[] ans = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max_sum = Integer.MIN_VALUE;

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            accum[i] = i>0? accum[i-1]+arr[i] : arr[i];
            ans[i] = accum[i];
            max_sum = Math.max(max_sum, ans[i]);
        }

        int min_accum = accum[0];
        for(int i=1; i<N; i++){
            if(min_accum<0){
                ans[i] = accum[i]-min_accum;
                max_sum = Math.max(max_sum, ans[i]);
            }
            min_accum = Math.min(min_accum, accum[i]);
        }
//        System.out.println(Arrays.toString(accum));
//        System.out.println(Arrays.toString(ans));
        System.out.println(max_sum);


    }

}

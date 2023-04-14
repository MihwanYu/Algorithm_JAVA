package NONCLASS;

import java.io.*;
import java.util.*;

public class p1654 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] sticks = new int[N];

        long max = 0;
        for(int i=0; i<N; i++){
            sticks[i] = Integer.parseInt(br.readLine());
            if(sticks[i]>max) max = sticks[i];
        }

        long start = 1;
        long end = max+1;
        long answer = 0;//처음 시작 값 초기화: 가장 긴 막대 길이

        if(K==1){
            System.out.println(max); return;
        }

        while(start<end){
            long mid = (start+end)/2;

            int cnt_sticks = 0;
            for(int i=0; i<N; i++) {
                cnt_sticks += sticks[i] / mid;
            }
            if(cnt_sticks<K){
                end = mid;
            }else{
                start = mid+1;
                if(mid>answer) answer = mid;
            }

        }

        System.out.println(answer);


    }
}

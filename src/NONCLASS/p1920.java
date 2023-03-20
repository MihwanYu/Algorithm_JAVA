package NONCLASS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1920 {
    static int[] integers, search;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        //recursion 메모리 초과 -> while 반복으로 해결
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;

        N = Integer.parseInt(br.readLine());
        integers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            integers[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        search = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            search[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(integers);

        sb = new StringBuilder("");
        for(int i=0; i<M; i++){
            binarysearch(0, N-1, search[i]);
        }

//        sb.deleteCharAt(sb.length()-1);
//        System.out.println(sb.toString());
    }

    static void binarysearch(int start, int end, int searchnum){
        /*
        int centerIdx = (start+end)/2;
        if(integers[centerIdx]==searchnum){
//            sb.append(1).append("\n");
            System.out.println(1);
            return;
        }if(start==end){
//            sb.append(0).append("\n");
            System.out.println(0);
            return;
        }
        if(searchnum < integers[centerIdx]){
            binarysearch(start, centerIdx-1, searchnum );
        }else{
            binarysearch(centerIdx+1, end, searchnum);
        }
        */

        while(start<=end){
            int centerIdx = (start+end)/2;
            if(integers[centerIdx] == searchnum){
                System.out.println(1);
                return;
            }else if(searchnum < integers[centerIdx]){
                end = centerIdx - 1;
            }else{
                start = centerIdx + 1;
            }
        }
        System.out.println(0);
        return;
    }
}

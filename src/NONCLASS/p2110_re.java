package NONCLASS;

import java.io.*;
import java.util.*;

public class p2110_re {
    //하아아아ㅏ아아ㅏ아ㅏ 이분탐색 공부 다시 하고와서 다시풀기!!!!!!!!!!!!!!!
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        //N개의 집, C개의 공유기
        //가장 인접한 공유기 사이의 거리를 최대로 할 때, 최대 거리는?

        Arrays.sort(arr);

        //1 2 4 8 9      3개의 공유기
        //1 4 8 => 거리 3

        //거리가 8일때 -> 공유기 2
        //거리가 4일때 ->


    }
}

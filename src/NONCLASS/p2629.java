package NONCLASS;

import java.io.*;
import java.util.*;

public class p2629 {
    static int N, M;
    static int[] marbles;
    static boolean[][] dp;
    public static void main(String[] args) throws Exception {
        //알고리즘 분류: dp. dfs로 했다가 해결할수 없는 반례가 있었다
        //냅색 스타일 dp
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        marbles = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            marbles[i] = Integer.parseInt(st.nextToken());
        }
        dp = new boolean[N+1][15001];

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder("");

        dynamicprogramming(0,0);//idx 0, gram 0일때부터 전체 확인

        for(int i=0; i<M; i++){
            int gram = Integer.parseInt(st.nextToken());
            //추 최대 500g, 최대 30개 -> 확인가능한 최대 무게 500*30
            char answer = gram<=15000 && dp[N][gram] ? 'Y' : 'N';
            sb.append(answer).append(" ");
//            System.out.println(dp[N][gram]);
        }
        System.out.println(sb.toString());

    }

    static void dynamicprogramming(int idx, int gram){

        if(dp[idx][gram]) return;//이미 확인했을 경우
        dp[idx][gram] = true;

        if(idx==N) return;
        //현재 gram에 idx번째 구슬 추가했을 때
        dynamicprogramming(idx+1, gram+marbles[idx]);

        //idx번째 구슬 반대편에 올릴 때
        dynamicprogramming(idx+1, Math.abs(gram-marbles[idx]));

        //idx번째 구슬 올리지 않을 때
        dynamicprogramming(idx+1, gram);
    }


}

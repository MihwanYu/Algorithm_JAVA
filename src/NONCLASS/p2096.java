package NONCLASS;

import java.io.*;
import java.util.*;
public class p2096 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][3];
        int[][] dp_min = new int[N][3];
        int[][] dp_max = new int[N][3];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(i==0){
                    dp_min[i][j] = board[i][j];
                    dp_max[i][j] = board[i][j];
                }
            }
        }

        for(int i=1; i<N; i++){
            //dp_max 최대 점수 구하기
            //col 0: 0, 1 중 비교
            //col 1: 0,1,2 중 비교
            //col 3: 1,2 중 비교
            dp_max[i][0] = Math.max(dp_max[i-1][0], dp_max[i-1][1]) + board[i][0];
            dp_max[i][1] = Math.max(Math.max(dp_max[i-1][0], dp_max[i-1][1]), dp_max[i-1][2]) + board[i][1];
            dp_max[i][2] = Math.max(dp_max[i-1][2], dp_max[i-1][1]) + board[i][2];


            dp_min[i][0] = Math.min(dp_min[i-1][0], dp_min[i-1][1]) + board[i][0];
            dp_min[i][1] = Math.min(Math.min(dp_min[i-1][0], dp_min[i-1][1]), dp_min[i-1][2]) + board[i][1];
            dp_min[i][2] = Math.min(dp_min[i-1][2], dp_min[i-1][1]) + board[i][2];

        }

        int maxVal = Math.max(Math.max(dp_max[N-1][0], dp_max[N-1][1]), dp_max[N-1][2]);
        int minVal = Math.min(Math.min(dp_min[N-1][0], dp_min[N-1][1]), dp_min[N-1][2]);
        System.out.println(maxVal+" "+minVal);
//        for(int i=0; i<N; i++){
//            System.out.println(Arrays.toString(dp_max[i]));
//        }
    }
}

package NONCLASS;

import java.io.*;
import java.util.*;
public class p17070 {
    static int N, countall = 0;
    static int[][] grid;
    static int[] dr = {0,1,1}, dc = {1,0,1};
    public static void main(String[] args) throws Exception{
        //알고리즘 분류: dfs.... 열심히 dp로 풀어보려다가 포기했다
        //dp로 누군가 풀었던 내용
        // https://www.acmicpc.net/source/28862445
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //처음 파이프 위치: (0,0)-(0,1)
        dfs(0,1,0);
        //목표: 끝이 (N-1,N-1)이 되는 것
        System.out.println(countall);
    }

    static void dfs(int endR, int endC, int dir){
        //dir 0: 가로 방향 1: 세로 방향 2: 대각선 방향
        if(endR==N-1 && endC==N-1){
            countall++;
            return;
        }

        //dir 0 -> 0, 2 방향 가능
        //dir 1 -> 1, 2 방향
        //dir 2 -> 0, 1, 2 방향
        for(int i=0; i<3; i++){
            if(dir==0 && i==1) continue;
            else if(dir==1 && i==0) continue;

            int nr = endR + dr[i];
            int nc = endC + dc[i];

            // (nr,nc)공간이 비어있을 경우 -> 탐색

            // dir==2일 때는 (nr,nc)외 2곳도 비어있는지 확인 후 탐색

            if(nr<N && nc<N && grid[nr][nc]==0){
                if(i==2 && !(grid[nr][nc-1]==0 && grid[nr-1][nc]==0)) continue;
                dfs(nr, nc, i);
            }
        }

    }
}

package NONCLASS;

import java.io.*;
import java.util.*;
public class p1520 {
    static int N, M, answer;
    static int[][] grid, dp;
    static boolean[][] visited;
    static int[] dr={0,1,0,-1}, dc={1,0,-1,0};
    static class Point{
        int r,c;
        Point(int r, int c){
            this.r = r; this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()) ;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //** -1로 초기화 이유: dp[][]==0이면 방문경험o, 경우의수0인것 확인가능 ..어렵다
        //   0으로 초기화 -> dp[][]==0일때 방문 여부도 알수 x, -> 시간 초과
        grid = new int[N][M];
        dp = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;//-1로 초기화
            }
        }


        answer = dfs(0,0);
        System.out.println(answer);

    }

    static int dfs(int r, int c){
        if(r==N-1 && c==M-1){
            return 1;
        }
        if(dp[r][c]>=0) return dp[r][c];

        int count = 0;
        for(int i=0; i<4; i++){
            int nr = r+dr[i];
            int nc = c+dc[i];
            //(nr,nc)가 범위 내에 있고 && 현 위치보다 낮은 곳이라면
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] < grid[r][c]) {
                count += dfs(nr,nc);
            }
        }

        dp[r][c] = count;
        return count;
    }

}

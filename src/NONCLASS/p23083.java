package NONCLASS;

import java.io.*;
import java.util.*;
public class p23083 {
    static int N, M, h;
    static int grid[][];
    static long dp[][];
//    static boolean visited[][];
    static int[] dr = {1,0,1,-1}, dc = {0,1,1,1};//아래, 옆, 옆(홀수일때만), 옆(짝수일때만)
    static class Point{
        int x, y;
        Point(int x, int y){this.x = x; this.y = y;}
    }
    public static void main(String[] args) throws Exception{
        //grid에서 dp 풀 때는 -1로 초기화를 하고 시작하기. 0이면 시간초과임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M  = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(br.readLine());

        grid = new int[N][M]; //0, -1로 채워짐
        dp = new long[N][M]; //초기값 -1
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                dp[i][j] = -1;
            }
        }

        for(int i = 0; i<h; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            grid[x-1][y-1] = -1;
        }

        //(0,0) -> (N-1, M-1)까지 이동하는 경로의 개수
        long answer = dfs(0,0);
        //10^9+7로 나눈 나머지 값으로 리턴해야 함 - 오버플로우 고려

//        printgrid();
        System.out.println(answer%(1000000007));

    }

    static long dfs(int x, int y){
        //dp 메모이제이션 사용할 수 있도록 짜기
        if(x==N-1 && y==M-1){
            dp[x][y] = 1;
            return 1;
        }
        if(dp[x][y]!=-1){
            //방문한 적 있다면(memoization되어있음)
            return dp[x][y];
        }

        //방문한 적 없는 꿀벌집
        long count = 0;

        for(int i=0; i<3; i++){
            int nr = x+dr[i];
            int nc = y+dc[i];
            if(i==2 && y%2==0){
                //짝수 열(문제의 홀수 열)에 대해 탐색할 오른쪽
                nr = x+dr[3];
                nc = y+dc[3];
            }

            if(nr>=0 && nr<N && nc>=0 && nc<M && grid[nr][nc]==0 ){
                //방문가능한 유효한 범위 내의 (nr,nc)에 방문한 적 없다면
                long ans = dfs(nr, nc);
                count += ans;
            }
        }

        dp[x][y] = count%1000000007;

//        System.out.println("처음 방문하는 "+x+","+y);
//        for(int i=0; i<N; i++){
//            for(int j=0; j<M; j++){
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        return count;
    }

    static void printgrid(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}

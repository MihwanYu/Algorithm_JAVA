package CLASS3;

import java.io.*;
import java.util.*;
public class p7569_re {
    static int M, N, H;
    static int[][][] grid, visited;
    static int[] dr = {1,0,-1,0,0,0}, dc = {0,1,0,-1,0,0}, dh = {0,0,0,0,1,-1};
    static class Point{
        int h, r, c;
        Point(int h, int r, int c){this.h = h; this.r = r; this.c = c;}
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //상자 크기, 익은 토마토, 익지 않은 토마토가 주어짐
        //토마토가 모두 익게 되는 최소 일수
        //1: 익은 토마토 0: 익지 않은 토마토 -1: 토마토 없는 칸
        //처음부터 모두 익어있으면 0, 절대 모두 익지 못하면 -1

        M = Integer.parseInt(st.nextToken());//c
        N = Integer.parseInt(st.nextToken());//r
        H = Integer.parseInt(st.nextToken());//h

        grid = new int[H][N][M];
        visited = new int[H][N][M];
        int not_mature = 0;
        int mature = 0;
        int no_tomato = 0;
        Queue<Point> tomatoes = new LinkedList<>();
        for(int h=0; h<H; h++){
            for(int n=0; n<N; n++){
                st = new StringTokenizer(br.readLine());
                for(int m=0; m<M; m++){
                    grid[h][n][m] = Integer.parseInt(st.nextToken());
                    if(grid[h][n][m]==1){//익은 토마토
                        tomatoes.add(new Point(h,n,m));
                        visited[h][n][m] = 1;
                        mature++;
                    }else if(grid[h][n][m]==0){//안익은 토마토
                        not_mature ++;
                    }else{
                        no_tomato ++;
                    }
                }
            }
        }
        if(not_mature==0){
            //처음부터 다 익어있을 경우
            System.out.println(0);
            return;
        }

        //tomatoes: day N에 익은 모든 토마토들

        // tomatoes의 인접한 애들 중 방문 안한거(다음날 익을 애들) 모두 new tomatoes에 추가하기
        int day = 1;
        while( !tomatoes.isEmpty() ){
            Point cur = tomatoes.poll();
            //새로운 좌표에 방문 처리 및 익음 처리
            for(int i=0; i<6; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];
                int nh = cur.h+dh[i];
                //인접한 곳에 익지 않은 토마토가 있고, (방문한 적 없다면)
                if(nr>=0 && nr<N && nc>=0 && nc<M && nh>=0 && nh<H && grid[nh][nr][nc]==0 && visited[nh][nr][nc]==0){
                    //처음 방문하는 토마토 익혀 주고 방문 처리, 방문 날짜 업데이트
                    grid[nh][nr][nc] = 1;
                    mature++;
                    visited[nh][nr][nc] = visited[cur.h][cur.r][cur.c]+1;
                    if(visited[nh][nr][nc]>day) day = visited[nh][nr][nc];
                    tomatoes.add(new Point(nh, nr, nc));
                }
            }
        }

        //방문하지 못한 토마토가 있으면 -> -1
        boolean is_all_matured = no_tomato + mature == H*N*M ? true : false;
        if(is_all_matured){
            System.out.println(day-1);
        }else{
            System.out.println(-1);
        }






    }
}

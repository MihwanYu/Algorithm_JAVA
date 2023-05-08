package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class p14502 {
    static int N, M;
    static int maxArea;
    static int[][] grid;
    static ArrayList<Point> virusOrigin;
    static class Point{
        int r, c;
        Point(int r, int c){this.r = r; this.c = c;}
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        //N, M<=8  => 브루트포스 가능?
        virusOrigin = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j]==2){
                    virusOrigin.add(new Point(i,j));
                }
            }
        }


        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(grid[i][j]==0){
                    grid[i][j] = 1;
                    makewall(1, i, j);
                    grid[i][j] = 0;
                }
            }
        }

        System.out.println(maxArea);
    }

    static void makewall(int count, int preR, int preC){
        if(count==3){
            //벽 3개 세우면 바이러스 확산 시작
            int cnt = expandVirus();
            if(cnt>maxArea){
                maxArea = cnt;
            }
            return;
        }

        for(int i=preR; i<N; i++){
            for(int j=0; j<M; j++){
                if(i==preR && j<=preC) continue;
                if(grid[i][j]==0){
                    grid[i][j] = 1;
                    makewall(count+1, i, j);
                    grid[i][j] = 0;
                }
            }
        }


    }

    static int expandVirus(){
        boolean[][] visited = new boolean[N][M];
        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,1,-1};
        //OriginVirus 확산: virus하나씩 순서대로 확산시킴
        for(int v=0; v< virusOrigin.size(); v++){
            Point p = virusOrigin.get(v);

            Queue<Point> queue = new LinkedList<>();
            queue.add(p);
            visited[p.r][p.c]=true;

            while( !queue.isEmpty() ){
                Point cur = queue.poll();

                for(int i=0; i<4; i++){
                    int nr = cur.r+dr[i];
                    int nc = cur.c+dc[i];
                    //유효한 범위 안의 (nr,nc)좌표에 빈 공간이 있고, 방문 기록이 없으면 -> 방문
                    if(nr>=0 && nr<N && nc>=0 && nc<M && grid[nr][nc]==0 && !visited[nr][nc]){
                        queue.add(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }

                }

            }

        }
        //3개 바이러스의 확산 마침
        //0 몇개인지 세기
        int count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                //빈 공간 + 방문하지 않았을 때(확산x)
                if(grid[i][j]==0 && !visited[i][j]) count++;
            }
        }
        return count;
    }
}

package NONCLASS;

import java.io.*;
import java.util.*;
public class p1926 {
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};

    static class Point{
        int r, c;
        Point(int r, int c){this.r = r; this.c = c;}
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxArea = 0, count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j] || grid[i][j]==0) continue;
                int area = bfs(i,j);
                if(maxArea < area) maxArea = area;
//                System.out.println("start: ("+i+","+j+"), area: "+area);
                count++;
            }
        }

        System.out.println(count);
        System.out.println(maxArea);
    }

    static int bfs(int i, int j){
        //i, j부터 시작해서 인접한 곳들
        int area = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i,j));

        while( !queue.isEmpty() ){
            Point cur = queue.poll();
            if(visited[cur.r][cur.c]) continue;
            visited[cur.r][cur.c] = true;
            area ++;

            for(int k=0; k<4; k++){
                int nr = cur.r+dr[k];
                int nc = cur.c+dc[k];

                //범위 내 (nr,nc)에 대해 grid는 1이고, visited는 false일 경우
                if(nr>=0 && nr<N && nc>=0 && nc<M && grid[nr][nc]==1 && !visited[nr][nc]){
                    queue.add(new Point(nr,nc));
                }

            }

        }


        return area;
    }
}

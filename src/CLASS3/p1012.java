package CLASS3;

import java.io.*;
import java.util.*;
public class p1012 {

    static int[][] grid;
    static boolean[][] visited;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};

    static class Point{
        int r, c;
        Point(int r, int c){this.r = r; this.c = c;}
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //배추흰지렁이는 상하좌우 인접한 모든 배추를 보호한다
        int testcase = Integer.parseInt(br.readLine());
        for(int t=0; t<testcase; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            grid = new int[M][N];
            visited = new boolean[M][N];
            for(int k=0; k<K; k++){
                //K개의 배추 위치 입력 및 grid에 저장(1)
                st = new StringTokenizer(br.readLine());
                grid[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            }

            int count = 0;
            for(int i=0; i<M; i++){
                for(int j=0; j<N; j++){
                    //방문한 적 없는 배추 좌표 (i,j)에 대해서 방문 처리
                    if(grid[i][j]==1 && !visited[i][j]){
                        bfs(i,j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    static void bfs(int i, int j){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i,j));

        while( !queue.isEmpty() ){
            Point cur = queue.poll();
            //현 포인트 배추 방문 처리: 이미 방문 했다면 넘어가고 아니면 인접 배추 확인
            if(visited[cur.r][cur.c]) continue;
            visited[cur.r][cur.c] = true;
            for(int k=0; k<4; k++){
                int nr = cur.r+dr[k];
                int nc = cur.c+dc[k];

                //(M,N)사이즈 격자 내에 있는 (nr,nc)좌표에 배추가 있고, 방문한 적 없다면
                if(nr>=0 && nr<grid.length && nc>=0 && nc<grid[0].length && grid[nr][nc]==1 && !visited[nr][nc]){
                    //방문 리스트에 추가
                    queue.add(new Point(nr,nc));
                }
            }
        }


    }



}

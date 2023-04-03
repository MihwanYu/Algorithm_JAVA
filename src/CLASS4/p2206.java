package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2206 {
    static class Point{
        int r, c;
        boolean iswallbroken;
        Point(int r, int c){
            this(r, c, false);
        }
        Point(int r, int c, boolean iswallbroken){
            this.r = r; this.c = c;
            this.iswallbroken = iswallbroken;
        }
    }
    static int N, M;
    static int[][] visited, visited2, grid;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M]; // 0, 1 인풋
        visited = new int[N][M]; // 벽 안 부시고 최단 거리
        visited2 = new int[N][M];// 벽 부시고 최단 거리

        //(0,0) -> (N-1, M-1)로 이동 최단 거리
        for(int n=0; n<N; n++){
            char[] line = br.readLine().toCharArray();
            for(int m=0; m<M; m++){
                grid[n][m] = line[m] - '0';
                visited[n][m] = 1000001; //max initialize
                visited2[n][m] = 1000001; //max initialize
            }
        }

        //시작점 위치 카운팅
        visited[0][0] = 1;
        int answer = bfs();
        System.out.println(answer);
//        for(int n=0; n<N; n++){
//            System.out.println(Arrays.toString(visited[n]));
//        }
    }

    static int bfs(){
        //첫 시작은 벽 부순 경험 x, 0,0
        Point start = new Point(0,0);

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        while( !queue.isEmpty() ){

            Point cur = queue.poll();
            //curlen: (0,0)-> 현위치 거리, 벽 뿌수고 나서인지 아닌지 구분
            int curlen = cur.iswallbroken ? visited2[cur.r][cur.c] : visited[cur.r][cur.c];

            for(int i=0; i<4; i++){
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                //이동 가능 한 범위 내일 때
                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    //통로인 경우(0)
                    if(grid[nr][nc]==0){
                        //이전에 벽 뿌순적 없으면 && visited에서 최솟값 업데이트 가능할 경우
                        if( !cur.iswallbroken && curlen+1 < visited[nr][nc] ){
                            visited[nr][nc] = curlen+1;
                            queue.add(new Point(nr, nc, cur.iswallbroken));
                        }else if( cur.iswallbroken && curlen+1 < visited2[nr][nc] ){
                            //뿌순 경우 있다면 && visited2에서 최솟값 업데이트 가능할 경우
                            visited2[nr][nc] = curlen+1;
                            queue.add(new Point(nr, nc, cur.iswallbroken));
                        }

                    }else{
                        //새로운 장소에 벽이 있을 경우(1)
                        //뿌실 수 있고 && 뿌셔서 최솟값 구할 수 있으면 뿌시기
                        if( ! cur.iswallbroken && curlen+1 < visited2[nr][nc]){
                            visited2[nr][nc] = curlen+1;
                            queue.add(new Point(nr, nc, true));
                        }
                    }
                }
            }

        }
        // 벽 안 부시고 최솟값 vs 벽 부시고 최솟값
        int min = Math.min(Math.min(visited[N-1][M-1], visited2[N-1][M-1]), 1000001);
        if(min==1000001){
            return -1;
        }else{
            return min;
        }

    }
}

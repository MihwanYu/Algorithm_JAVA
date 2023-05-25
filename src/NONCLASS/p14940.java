package NONCLASS;

import java.io.*;
import java.util.*;
public class p14940 {
    static class Pair{
        int r, c;
        Pair(int r, int c){
            this.r = r; this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        int[][] visited = new int[R][C];

        int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};

        Pair start = null;
        for(int r=0; r<R; r++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(visited[r], -1);
            for(int c=0; c<C; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c]==2){
                    start = new Pair(r,c);
                }else if(map[r][c]==0){
                    visited[r][c] = 0;
                }
            }
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = 0;
        while( !queue.isEmpty() ){
            Pair cur = queue.poll();

            for(int i=0; i<4; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];

                //nr,nc가 범위 내에 있고, visited에서 -1이라면: 방문한적없다면
                if(nr>=0 && nr<R && nc>=0 && nc<C && visited[nr][nc]==-1){
                    visited[nr][nc] = visited[cur.r][cur.c]+1;
                    queue.add(new Pair(nr,nc));
                }
            }
        }

        StringBuilder sb = new StringBuilder("");
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                sb.append(visited[r][c]).append(" ");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());



    }
}

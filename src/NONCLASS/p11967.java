package NONCLASS;

import java.io.*;
import java.util.*;
public class p11967 {
    static int N, M;
    static class Point{
        int r, c;
        Point(int r, int c){this.r = r; this.c = c;}
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Point>[][] switches = new ArrayList[N][N];//각 방에 존재하는 스위치
        boolean[][] light = new boolean[N][N];//각 방의 불 켜짐 유무
        boolean[][] visited = new boolean[N][N];//방문여부

        for(int i=0; i<M; i++){
            String[] s = br.readLine().split(" ");
            int r = Integer.parseInt(s[0])-1;
            int c = Integer.parseInt(s[1])-1;
            if(switches[r][c] == null){
                switches[r][c] = new ArrayList<>();
            }
            switches[r][c].add(new Point(Integer.parseInt(s[2])-1, Integer.parseInt(s[3])-1));
        }

        int[] dr = {1,0,-1,0}, dc={0,1,0,-1};

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0));
        light[0][0] = true;
        int cntRoom = 0;

        while( !queue.isEmpty() ){
            Point cur = queue.poll();
            if( visited[cur.r][cur.c]) continue;

            visited[cur.r][cur.c] = true;
//            System.out.print("\n("+(cur.r+1)+","+(cur.c+1)+") ->");

            //switches[cur.r][cur.c]에 연결된 모든 방들의 불 다 키기: light[r][c] = true
            //불 킨 곳이 이전에 방문했던 곳과 하나라도 인접해있다면 -> 방금 불 킨 (r,c)를 queue에 추가
            ArrayList<Point> arr = switches[cur.r][cur.c];
            if(arr != null){
                for(int i=0; i<arr.size(); i++){
                    Point p = arr.get(i);
                    light[p.r][p.c] = true;
                    for(int d=0; d<4; d++){
                        int nr = p.r+dr[d];
                        int nc = p.c+dc[d];
                        if(nr>=0 && nr<N && nc>=0 && nc<N && visited[nr][nc]){
                            queue.add(p);
//                            System.out.print("("+(p.r+1)+","+(p.c+1)+") ");
                            break;
                        }
                    }
                }
            }

            //현 위치 인근에 방문한 적 없지만 불 켜져 있는 곳이 있다면 -> queue에 추가
            for(int d=0; d<4; d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];
                if(nr>=0 && nr<N && nc>=0 && nc<N && light[nr][nc] && !visited[nr][nc]){
                    queue.add(new Point(nr, nc));
//                    System.out.print("("+(nr+1)+","+(nc+1)+") ");
                }
            }

        }
//        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(light[i][j]) cntRoom++;
            }
        }

        System.out.println(cntRoom);



    }
}

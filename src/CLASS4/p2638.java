package CLASS4;

import java.util.*;
import java.io.*;

public class p2638 {
    static int N, M, hours;
    static int[] dr = {1,0,-1,0}, dc={0,1,0,-1};
    static int[][] grid;
    static class Point{
        int r, c;
        Point(int r, int c){this.r = r; this.c = c;}

        @Override
        public String toString() {
            return "P(" + r +
                    ", " + c +
                    ')';
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0,0, 0,-1);//치즈 바깥부분 0 -> -1로 바꾸기
//        printgrid();

        hours = 0;
        while(true){
            boolean ischeesemelt = melting();
            if( !ischeesemelt ) break;//더이상 녹을 치즈가 없으면
            hours++;
//            System.out.println(hours+" hours later");
//            printgrid();
        }
        System.out.println(hours);
    }

    static boolean melting(){
        boolean[][] visited = new boolean[N][M];
        ArrayList<Point> meltings = new ArrayList<>();
        ArrayList<Point> emptys = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(grid[i][j]==1 && !visited[i][j]){
                    //bfs 수행, visit 처리
                    //2면 이상 -1인 애들 meltings에 추가(grid를 0으로 바꾸면안된다)
                    bfs2(i,j,visited,meltings, emptys);
                }
            }
        }

        if(meltings.isEmpty()) return false;
//        System.out.println("melting 대상: ");
//        System.out.println(Arrays.toString(meltings.toArray()));
        //meltings에 있는 c들 한번에 grid값 -1로 만들기
        for(int i=0; i<meltings.size(); i++){
            grid[meltings.get(i).r][meltings.get(i).c] = -1;
        }
        //emptysize에 있는 빈 공간들 -1로 변경하는 bfs 사용
        for(int i=0; i<emptys.size(); i++){
            Point cur = emptys.get(i);
            bfs(cur.r, cur.c, 0,-1);
        }

        return true;
    }

    static void bfs2(int r, int c, boolean[][] visited, ArrayList<Point> meltings, ArrayList<Point> emptys){
        //2면 이상 -1인 애들 meltings에 추가
        //(r,c) 값은 1

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r,c));
        ArrayList<Point> innerSpace;

        while( !queue.isEmpty() ){
            Point cur = queue.poll();
            innerSpace = new ArrayList<>();

            if(visited[cur.r][cur.c]) continue;
            visited[cur.r][cur.c] = true;

            int adjacent = 0;
            for(int i=0; i<4; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];

                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if( !visited[nr][nc] && grid[nr][nc]==1){
                        queue.add(new Point(nr,nc));
                    }
                    else if(grid[nr][nc]==-1) adjacent++;
                    else if(grid[nr][nc]==0) innerSpace.add(new Point(nr,nc));//근처에 빈 공간 있으면 innerSpace에 추가
                }
            }

            //현위치에서 2면 이상 밖에 노출될 경우
            if(adjacent>=2){
                meltings.add(cur);//현위치 melting list 추가
                //현위치 녹으면 바깥공기 들어오는 innerSpace도 emptys 추가
                if( !innerSpace.isEmpty() ){
                    for(int i=0; i<innerSpace.size(); i++){
                        emptys.add(innerSpace.get(i));
                    }
                }
            }

        }


    }

    static void bfs(int r, int c, int originV, int changeV){
        //(r,c)부터 모든 값을 changeV 값으로 바꾸기

        boolean[][] visited = new boolean[N][M];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r, c));

        while( !queue.isEmpty() ){
            Point cur = queue.poll();

            if(visited[cur.r][cur.c]) continue;
            visited[cur.r][cur.c] = true;
            grid[cur.r][cur.c] = changeV;//값 바꾸기

            for(int i=0; i<4; i++){
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if( !visited[nr][nc] && grid[nr][nc]==originV){
                        queue.add(new Point(nr, nc));
                    }
                }
            }

        }
    }

    static void printgrid(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(grid[i][j]==1)
                    System.out.print("■ ");
                else if(grid[i][j]==-1)System.out.print(". ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }
}

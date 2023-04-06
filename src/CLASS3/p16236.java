package CLASS3;

import java.io.*;
import java.util.*;

public class p16236 {
    static int N, sharksize=2, sharkeatten=0, seconds=0;
    static int[] dr = {-1,0,0,1}, dc = {0,-1,1,0};
    static int[][] grid, minLength;
    static boolean hasFish = true;
    static Point shark;
    static class Point{
        int r, c, toshark;
        Point(int r, int c, int toshark){this.r = r; this.c = c; this.toshark = toshark;}

        @Override
        public String toString() {
            return "(" +
                    + r +
                    ", " + c +
                    "), ";
        }
    }
    public static void main(String[] args) throws Exception{
        //먹이 찾는 기준 세우기가 관건 ,,, 먹이-상어 장애물 고려 안하고 맨해튼 거리로 구했다가 망한 ..
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

//        Shark shark = null;
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j]==9) {
                    grid[i][j] = 0;//상어 자리 빈칸으로 바꿔주기
                    shark = new Point(i,j,0);
                }
            }
        }

        while(true){
            findFish(); //shark.r, shark.c가 가장 짧은 거리의 먹이로 이동
            if( !hasFish ) break; //더이상 남아 있는 fish 없으면 중단
            eatFish();
        }

//        System.out.println();
        System.out.println(seconds);

    }
    static void printgrid(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(grid[i][j]>0)
                System.out.print(grid[i][j]+" ");
                else if(i==shark.r && j==shark.c)
                    System.out.print("■ ");
                else System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    static void findFish(){
        //shark.r, shark.c에서 가장 짧은 거리의 먹이를 탐색함
        // 만약 더이상 먹을 수 있는 먹이가 없으면 hasFish = false
        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //장애물때문에 꺾인 거리를 계산해야 함
//                int o1_len = Math.abs(shark.r-o1.r)+Math.abs(shark.c-o1.c);
//                int o2_len = Math.abs(shark.r-o2.r)+Math.abs(shark.c-o2.c);

                if(o1.toshark != o2.toshark){
                    //거리가 가장 짧은 순
                    return Integer.compare(o1.toshark, o2.toshark);
                }else{
                    //둘중 더 위쪽인 애
                    if(o1.r != o2.r) return Integer.compare(o1.r, o2.r);
                    //둘중 더 왼쪽인 애
                    else return Integer.compare(o1.c, o2.c);
                }
            }
        });

        queue.add(new Point(shark.r, shark.c, 0));

        int visited[][] = new int[N][N];
        visited[shark.r][shark.c] = 1;//한번도 안가본 곳은 다 0

        while( !queue.isEmpty() ){
//            System.out.println(Arrays.toString(queue.toArray()));
            Point cur = queue.poll();

            //현재 위치에 shark의 크기보다 작은 물고기가 존재한다면
            if(grid[cur.r][cur.c] >0 && grid[cur.r][cur.c]<sharksize){
                shark.r = cur.r; shark.c = cur.c; //상어가 해당 위치로 이동
                seconds += visited[cur.r][cur.c]-1;//거리 바로 더해주기
//                System.out.println("find fish: "+cur.r+", "+cur.c+"  (size)"+sharksize+"   (sec)"+seconds);
                return ;
            }

            for(int i=0; i<4; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c + dc[i];
                if(nr>=0 && nr<N && nc>=0 && nc<N && visited[nr][nc]==0 ){
                    //범위 내에서 방문한 적 없는 장소 nr,nc에 대해서
                    if(grid[nr][nc]<=sharksize){
                        //이동 가능하다면 큐에 넣고 방문 처리 해주기
                        queue.add(new Point(nr, nc, visited[cur.r][cur.c]));
                        visited[nr][nc] = visited[cur.r][cur.c] +1;
                    }
                }
            }
        }
//        System.out.println("no fish");
        //작은 물고기 못 찾고 탐색이 끝났다면 더 이상 먹을 수 있는 물고기 없음
        hasFish = false;

    }

    static void eatFish(){
        sharkeatten ++;
        //크기 만큼 먹이 먹으면 크기 & 먹이 정보 갱신
        if(sharkeatten==sharksize){
            sharksize++; sharkeatten=0;
        }
        grid[shark.r][shark.c] = 0; //먹이 있던 자리 빈 공간


    }
}

package WEEKLY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p21922 {
    static int N, M, count;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dr = {1,0,-1,0}, dc={0,1,0,-1}; //[0]아래 [1]오른 [2]위 [3]왼

    static class Aircon{
        int r, c;
        public Aircon(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        visited = new boolean[N][M];

        Queue<Aircon> queue = new LinkedList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j]==9){
                    queue.add(new Aircon(i, j));
                }
            }
        }

        //에어컨 입력이 주어지지 않았을 때
        if(queue.size()==0){
            System.out.println(0);
            return;
        }

        while( !queue.isEmpty() ){
            Aircon curA = queue.poll();
            //각 에어컨에서 4방향 송풍
            for(int i=0; i<4; i++){
                int curR = curA.r ;
                int curC = curA.c ;
                int curD = i;
                visited[curR][curC] = true;

                while(true){
                    int nr = curR + dr[curD];
                    int nc = curC + dc[curD];
                    int nd = curD;
                    if(nr>=0 && nr<N && nc>=0 && nc<M){
                        if(grid[nr][nc]==9){
                            //새로운 위치에 에어컨 있으면
                            break;
                        }

                        //물체 있으면 방문
                        if(grid[nr][nc]!=0){
                            nd = findDirection(grid[nr][nc], curD);

                        }
                        //물체 있을때 & 없을때 모두 방문
                        visited[nr][nc] = true;
                        curR = nr;
                        curC = nc;
                        curD = nd;
                        //바람 반사되는(nd==-1) 경우 방문 처리만 하고 반복 종료
                        if(nd==-1) break;

                    }else{
                        //grid 범위 벗어나면
                        break;
                    }
                }
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]) count++;
            }
        }
//        printgrid();
        System.out.println(count);
    }

    //N, M <=2000이기 때문에 dfs 쓰면 런타임에러(stackoverflow) 발생
    static void dfs(int curR, int curC, int direction){
        if(! visited[curR][curC]){
            visited[curR][curC] = true;
            count ++;
        }

        if(direction==-1) return;

        int nr = curR + dr[direction];
        int nc = curC + dc[direction];
        if(nr >=0 && nr<N && nc>=0 && nc<M){
            if(grid[nr][nc]==0){
                dfs(nr, nc, direction);
            }else{
                int nd = findDirection(grid[nr][nc], direction);
                dfs(nr, nc, nd);
            }
        }
    }

    static int findDirection(int obj, int dir){
        if(obj==1){
            if(dir==1 || dir==3){
                return -1;
            }else return  dir;
        }else if(obj==2){
            if(dir==0 || dir==2){
                return -1;
            }else return dir;
        }else if(obj==3){
            return 3-dir;
            //[0]아래 [1]오른 [2]위 [3]왼
            /*
            if(dir==0) return 3;//아래 -> 왼
            if(dir==1) return 2;//오른 -> 위
            if(dir==2) return 1;//위 -> 오른
            if(dir==3) return 0;//왼 -> 아래
            */

        }else{
            //아래 -> 오른
            //오른 -> 아래
            //왼 -> 위
            //위 -> 왼
            if(dir==0) return 1;
            if(dir==1) return 0;
            if(dir==2) return 3;
            if(dir==3) return 2;
        }
        return -1;
    }

    static void printgrid(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]){
                    System.out.print("+");
                }else{
                    System.out.print("_");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}

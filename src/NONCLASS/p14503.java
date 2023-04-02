package NONCLASS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14503 {
    static int N, M;
    static int r,c,d;
    static int count;
    static int grid[][], backvisit[][];
    static boolean visited[][];
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; //[0]북 [1]동 [2]남 [3]서 i++, 시계방향
    public static void main(String[] args) throws Exception{
        //구현은 금방했는데 후진방법 문제이해만 한세월걸린,,
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        visited = new boolean[N][M];
        backvisit = new int[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                backvisit[i][j] = 0;
            }
        }

        count = 0;
        dfsrotate(r,c,d);
        System.out.println(count);
    }

    static void dfsrotate(int curR, int curC, int curD){
        if(! visited[curR][curC]){
            //처음방문(=후진x) 현 위치 방문 처리
            visited[curR][curC] = true;
            count++;
//            printgrid();
        }


        //시계반대방향 rotation
        int visitR=curR, visitC=curC, visitD = curD;
        boolean isvisit = false;
        for(int i=0; i<4; i++){
            visitD = (visitD+3)%4; //방향 회전: 반시계
            visitR = curR + dr[visitD];
            visitC = curC + dc[visitD];

            //왼쪽 회전 방향 방문 가능한지 확인(grid[][]==0, visited[][] = false)
            if(visitR>=0 && visitR<N && visitC>=0 && visitC<M && grid[visitR][visitC]==0 && !visited[visitR][visitC]){
                //방문 가능하다면 for break, isvisit = true
                isvisit = true;
                break;
            }
        }

        //isvisit = true면 dfs로 해당 위치 방문
        if(isvisit){
            dfsrotate(visitR, visitC, visitD);
        }else{
            visitR = curR + dr[(visitD+2)%4];
            visitC = curC + dc[(visitD+2)%4];
            if(grid[visitR][visitC] == 0){
                //벽이 아니면 후진 가능
                dfsrotate(visitR, visitC, visitD);
            }else{
                //벽이 맞으면 종료
            }

        }
    }

    static void printgrid(){
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(grid[r][c]==1){
                    System.out.print("□ ");
                }else if(visited[r][c]){
                    System.out.print("■ ");
                }else{
                    System.out.print("_ ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }


}

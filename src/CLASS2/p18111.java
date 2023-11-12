package CLASS2;

import java.io.*;
import java.util.*;

public class p18111 {
    static int N, M, B;
    static int[][] grid;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //NxM 사이즈 집터(0,0)부터 시작)
        //땅의 높이를 일정하게 바꾸기
        //(i,j)의 블록 제거 -> 인벤토리에 넣기 : 2초
        //인벤토리에서 꺼내서 -> (i,j) 가장 위에 놓기 : 1초

        //출력: 최소 시간과 땅의 높이 (가능한 가장 높은 땅의 높이)
        //처음에 B개의 블록이 인벤토리에 있음
        //0<높이<=256

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int minH = 256, maxH = 0;

        grid = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j]>maxH) maxH = grid[i][j];
                else if(grid[i][j]<minH) minH = grid[i][j];
            }
        }

        //집터의 블록 제거 시간: 2초
        //집터에 블록 샇는 시간: 1초

        //높이 최솟값 >>>> 최댓값 올려가면서
        //각각 시간 확인하기
        int minTime = Integer.MAX_VALUE;
        int height = minH;

        for(int h=minH; h<=maxH; h++){
            int time = getTime(h);
//            System.out.println("height: "+h+" , time: "+time);
            if(time ==-1) continue;
            if(time<=minTime) {
                minTime = time;
                height = h; //최소 시간보다 같거나 작을때 height 무조건 업데이트(동일 시간에선 큰 높이 우선)
            }

        }
        System.out.println(minTime + " " + height);

    }

    static int getTime(int h){
        //push: 쌓아야 하는 블록 수, pop: 빼야 하는 블록 수
        int push = 0, pop = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(grid[i][j]>h){
                    pop+= grid[i][j]-h;
                }else if(grid[i][j]<h){
                    push+= h-grid[i][j];
                }
            }
        }
        //인벤토리에 쌓을 수 있는 블록보다 빼서 쓸 블록이 많으면
        //B: 처음 재고
        //push: 재고에서 뺄 양(땅에 쌓을 블록)
        //pop: 재고에 넣을 양
        if(push > pop + B) return -1;
        else{
            int time = push + pop*2;
            return time;
        }


    }

}

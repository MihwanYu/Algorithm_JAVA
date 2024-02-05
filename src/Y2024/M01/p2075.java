package Y2024.M01;


import java.io.*;
import java.util.*;
public class p2075 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 1<=N<=1500, grid[i][j] <1,000,000,000
        //모든 수는 한 칸 위 수보다 크다(grid[i][j] > grid[i][j-1])
        //N번째 큰 수를 찾기

        /*
        5                                       12 13 21 48 52, col(0) min: 12
        12 7 9 15 5                          14  20  21  48  52     col(1) min: 14
        13 8 11 19 6                         26  28  32  48  52     col(2) min: 26
        21 10 26 31 16                       32  35  41  48  52     col(3) min: 32
        48 14 28 35 25                       35  41  48  49  52     col(4) min: 35
        52 20 32 41 49
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            pq.add(grid[i][0]);
        }

        //pq size는 항상 5 유지
        for(int j=1; j<N; j++){
            //col j에 대해서
            int pqMin = pq.peek();
            for(int i=N-1; i>=0; i--){
                if(grid[i][j]>pqMin){
                    pq.poll();//맨 앞 하나 빼고
                    pq.add(grid[i][j]);// 새로운 값 추가하고
                    pqMin = pq.peek();// 최솟값 업데이트
                }else break;
            }
        }
        System.out.println(pq.poll());



    }
}

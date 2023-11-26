package CLASS3;

import java.io.*;
import java.util.*;

public class p1389_re {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //케빈 베이컨의 수: 모든 사람과 케빈 베이컨 게임을 했을 때, 나오는 단계의 합.
        //케빈 베이컨의 수가 가장 작은 사람 출력(그 중 번호가 제일 작은 사람)
        int[][] users = new int[N+1][N+1];
        //2차배열 초기화(무한값)
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j) continue;
                users[i][j] = Integer.MAX_VALUE;
            }
        }

        //연결됐을 때 1
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            users[v][u] = 1;
            users[u][v] = 1;
        }
        
        //플로이드 워셜 3중 for문은 for(연결점 mid for(시작점 src for(도착점 dst)))여야 한다......걍외우자
        for(int mid=1; mid<=N; mid++) {
            for (int src = 1; src <= N; src++) {
                for (int dst = 1; dst <= N; dst++) {
                    if (src == dst) continue;

                    if (src == mid || dst == mid) continue;
                    if (users[src][mid] == Integer.MAX_VALUE || users[mid][dst] == Integer.MAX_VALUE) continue;
                    users[src][dst] = Math.min(users[src][dst], users[src][mid] + users[mid][dst]);

                }
            }
        }

        int smallest = Integer.MAX_VALUE;
        int idx = 0;
        for(int i=1; i<=N; i++){
            //users[i]의 [1]~[N]까지의 sum 구하기 및 smallest와 비교
            int total = 0;
            for(int j=1; j<=N; j++){
                total += users[i][j];
            }
            if(total<smallest){
                smallest = total; idx = i;
            }
        }
        System.out.println(idx);

    }

}

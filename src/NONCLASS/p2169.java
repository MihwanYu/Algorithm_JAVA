package NONCLASS;
import java.io.*;
import java.util.*;


//아래,왼&오 모두 이동 가능할 때의 dp
// -> row별로 오른쪽 or 아래로 이동 최댓값 & 왼쪽 이동 최댓값 구한 후 put max value
//ref: https://wooono.tistory.com/605 혼자 못풂 ..
public class p2169 {
    static int N, M;
    static int[][] map, dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //map initializing
        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp initializing : num range from -100 to +100
        dp = new int[N][M];
        dp[0][0] = map[0][0];

        for(int i = 1 ; i < M ; i++)
            dp[0][i] = dp[0][i-1]+ map[0][i];

        for(int i = 1 ; i < N ; i++) {
            int temp1 [] = new int [M];//from left -> to right
            int temp2 [] = new int [M];//from right -> to left

            for(int j = 0 ; j < M ; j++){
                if(j==0){//case (i,0)
                    temp1[0] = dp[i-1][0] + map[i][0];
                    continue;
                }
                //오른쪽 방향 이동 VS 아래 방향 이동 중 최댓값
                temp1[j] =map[i][j]+ Math.max(temp1[j-1], dp[i-1][j]);
            }

            for(int j = M-1 ; j >= 0 ; j--){
                if(j==M-1){
                    temp2[M-1] = dp[i-1][M-1] + map[i][M-1];
                    continue;
                }
                //이전에 구한 최댓값 VS 왼쪽 방향 이동 최댓값
                temp2[j] = map[i][j] + Math.max(temp2[j+1] , dp[i-1][j]);
            }

            for(int j = 0 ; j < M ; j++)
                dp[i][j] = Math.max(temp1[j], temp2[j]);
        }

        //compare max val with prior val VS from right->left
        System.out.println("\nmap");
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
        }


        System.out.println("dp");
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.printf("%4d", dp[i][j]);
            }
            System.out.println();
        }

        System.out.println(dp[N-1][M-1]);


    }
}

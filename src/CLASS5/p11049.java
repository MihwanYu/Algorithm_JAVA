package CLASS5;

import java.io.*;
import java.util.*;

public class p11049 {
    public static void main(String[] args) throws Exception{
        //한 2번정도 풀이 시도했다가 말았던 문제
        //다시 봐도 접근 방법 도저히 감이 안잡혀서 솔루션 참고함(베낌)
        //https://www.acmicpc.net/problem/11049
        //정말 어렵다 나중에 다시 봐도 못 풀거같음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] matSize = new int[N+1][2];
        int[][] dp = new int [N+1][N+1];//dp[x][y]: mat x~y 구간 연산 최솟값
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            matSize[i][0] = Integer.parseInt(st.nextToken());
            matSize[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++)
        {
            for (int j = 1; i + j <= N; j++)
            {
                dp[j][i + j] = Integer.MAX_VALUE;
                for (int k = j; k < i + j; k++)
                {
//                    System.out.println("i:"+i+" , j:"+j+" , k:"+k);
                    dp[j][i+j] = Math.min(dp[j][i+j], dp[j][k] + dp[k+1][i+j] + matSize[j][0]*matSize[k][1]*matSize[i+j][1]);
                }
            }
        }
        System.out.println(dp[1][N]);


    }
}

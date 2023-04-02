package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2407 {
    public static void main(String[] args) throws Exception {
        // nCr = n! / (n-r)! r!
        // dp: 파스칼 삼각형

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // * long 범위 넘어서는 정수
        BigInteger dp[][] = new BigInteger[n+1][n+1];

        // * 파스칼 삼각형 생성
        for(int i=1; i<=n; i++){
            for(int j=0; j <= i; j++){
                if(j==0 || j==i){
                    dp[i][j] = BigInteger.valueOf(1);
                }
                else{
                    dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]);
                }
            }
        }
        System.out.println(dp[n][m]);

    }

}

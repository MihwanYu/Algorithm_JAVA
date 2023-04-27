package CLASS3;
import java.io.*;
import java.util.*;

public class p9461 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //N범위 100까지지만 dp니까 오버플로우 발생.
        int testcase = Integer.parseInt(br.readLine());
        for(int t=0; t<testcase; t++){
            int n = Integer.parseInt(br.readLine());
            int answer = getAnswer(n);
            System.out.println(answer);
        }
    }
    // 1 1 1 2 2 3 4 5 7 9 12 16 21
    static int getAnswer(int n){
        if(n<=3) return 1;
        else if(n<=5) return 2;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for(int i=6; i<=n; i++){
            dp[i] = dp[i-5] + dp[i-1];
        }

        return dp[n];
    }
}

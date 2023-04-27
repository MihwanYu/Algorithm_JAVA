package CLASS3;

import java.io.*;
import java.util.*;

public class p9095 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for(int t = 0; t<testcase; t++){
            int n = Integer.parseInt(br.readLine());
            int cases = getcases(n);

            System.out.println(cases);
        }

    }

    static int getcases(int n){
        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i=4; i<=n; i++){
            dp[i] = dp[i-3]+dp[i-2]+dp[i-1];
        }
//        System.out.println(Arrays.toString(dp));
        return dp[n];

    }

}

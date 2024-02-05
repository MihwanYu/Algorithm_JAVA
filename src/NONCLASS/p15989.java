package NONCLASS;

import java.io.*;
import java.util.*;

public class p15989 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //n을 1,2,3의 합으로 나타내는 방법의 수
        //ㅠㅡㅠ전에도 솔루션 참고했던 것 같은데 ..
        int tc = Integer.parseInt(br.readLine());
        for(int i=0; i<tc; i++){
            int N = Integer.parseInt(br.readLine());
            int[] dp = new int[N+1]; //n<=10000
            if(N<=3) {
                System.out.println(N);
                continue;
            }
            dp[1] = 1; dp[2] = 2; dp[3] = 3;
            for(int n=4; n<=N; n++){
                dp[n] = dp[n-3] + 1 + n/2;
            }
            System.out.println(dp[N]);

            //1 = 1                                                 1
            //2 = 1+1, 2                                            2
            //3 = 1+1+1, 2+1, 3                                     3

            //4 = 1+1+1+1, 1+1+2, 1+3, 2+2                          4
            //5 = 1+1+1+1+1, 1+1+1+2, 1+1+3, 1+2+2, 2+3             5  f(5) = f(4) + 1
            //6 = 1+1+1+1+1+1, 1+1+1+1+2, 1+1+1+3, 1+1+2+2, 1+2+3   7
            //    2+2+2, 3+3                                           f(6) = f(5) + 2

            //7 = 1+1+1+1+1+1+1, 1+1+1+1+1+2, 1+1+1+1+3, 1+1+1+2+2, 1+1+2+3   f(7) = f(6)+1 = 8
            //    1+2+2+2, 1+3+3
            //    2+2+3

            //8 = 1+1+1+1+1+1+1+1, 1+1+1+1+1+1+2, 1+1+1+1+1+3, 1+1+1+1+2+2, 1+1+1+2+3   f(8) = f(7)+2 = 10
            //    1+1+2+2+2, 1+1+3+3, 1+2+2+3
            //    2+2+2+2, 2+3+3

            //9 = 1+1+1+1+1+1+1+1+1, 1+1+1+1+1+1+1+2, 1+1+1+1+1+1+3, 1+1+1+1+1+2+2, 1+1+1+1+2+3   f(9) = f(8)+2 = 12
            //    1+1+1+2+2+2, 1+1+1+3+3, 1+1+2+2+3 1+2+2+2+2, 1+2+3+3
            //    2+3+3, 3+3+3

            //10 =                                          f(10) = f(9)+2 = 14
            //   1  2  3    4  5  6    7  8   9     10
            //   1, 2, 3,   4, 5, 7,   8, 10, 12,   14

        }



    }
}

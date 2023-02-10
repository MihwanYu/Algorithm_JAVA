package DAY10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11659 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N+1];
        int[] dp = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int n=1; n<N+1; n++){
            numbers[n] = Integer.parseInt(st.nextToken());
            dp[n] = dp[n-1] + numbers[n];
        }

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            System.out.println(dp[j]-dp[i-1]);
        }


    }
}

package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1697 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int dp[];
        int MAX = Integer.MAX_VALUE-1;

        if (N>=K){//N이 K보다 클 경우 K로 이동하는 방법: -1씩 이동하기만 가능, 같을 경우: 0
            System.out.println(N-K);
            return;
        }

        //dp 초기화
        dp = new int[K*2+1];
        for(int i=0; i<dp.length; i++){
            dp[i] = MAX;
        }
        //N보다 작은 값의 2배수 되는 숫자 값 채우기
        int count = 1;
        for(int n=N-1; n>0 && n*2>N; n--){
            if(!(n*2 > N && n*2 <dp.length)) break;
            dp[n] = count;
            dp[n*2] = count+1;
            count++;
        }
        dp[N] = 0;
        dp[N+1] = 1;
        if(N>0){
            dp[N*2] = 1;
        }


//        System.out.println(Arrays.toString(dp));
        //N->K 이동
        for(int n = N+1; n<=K; n++){
            if(n%2==0){
                dp[n] = Math.min(dp[n], dp[n-1]+1);
            }else{
                dp[n] = Math.min(dp[n-1]+1, dp[n+1]+1);
            }
            dp[2*n] = Math.min(dp[2*n], dp[n]+1);
        }

        System.out.println(dp[K]);

    }
}

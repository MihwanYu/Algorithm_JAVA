package NONCLASS;

import java.io.*;
import java.util.*;
public class p1106 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] costs = new int[N];
        int[] people = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            people[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[C+100];//홍보액에 따른 고객 수는 최대 100
        // -> C 이상의 고객 유치 비용 중 가장 작은 값 고를때 C~C+100중에서 보면 됨
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0; i<N; i++){
            int cost = costs[i];
            int peop = people[i];
            for(int c = peop; c<C+100; c++){
                if(dp[c-peop]==Integer.MAX_VALUE) continue;
                dp[c] = Math.min(dp[c], dp[c-peop]+cost);
            }
        }

        int minval = dp[C];
        for(int c=C; c<C+100; c++){
            if(dp[c]<minval){
                minval = dp[c];
            }
        }
        System.out.println(minval);

    }
}

package DAY5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1256 {
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char[] answer = new char[n+m];

        dp = new int[n+1][m+1];
        //dp[n][m] = dp[n-1][m] + dp[n][m-1]
        for(int tn=0; tn<=n; tn++){
            for(int tm=0; tm<=m; tm++){
                if(tn==0 || tm==0){
                    dp[tn][tm] = 1;
                }else{
                    //overflow 발생하는 문제 -> 어차피 k<10억이니까
                    dp[tn][tm] = Math.min(dp[tn-1][tm] + dp[tn][tm-1] , 1000000000);
                }

            }
        }

        if(dp[n][m]<k){
            System.out.println(-1);
            return;
        }

        int order = 0;
        boolean breakpoint = false;

        int i;
        for(i=0; i<answer.length; i++){

            if(n==0 || m==0) break;
            int fronthalf = dp[n-1][m];
            int backhalf = dp[n][m-1];

            //i번째 값이 a일 경우
            if(order + fronthalf >= k){
                answer[i] = 'a';

                n--;
            }else{
                answer[i] = 'z';
                order += fronthalf;
                m--;
            }

        }

//        System.out.println(Arrays.toString(answer));

        for( ; i<answer.length; i++){
            answer[i] = m==0? 'a':'z';
            order++;
        }

        StringBuilder sb = new StringBuilder();
        for(char a: answer){
            sb.append(a);
        }
        System.out.println(sb.toString());
        //100 100 1000000000
        String answer2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzazzazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzazzzzzzzzzzzzzzzzzzzzzzazzzzzazzzzzzzzzzzzazzzz";
//        System.out.println(answer2.equals(sb.toString()));




    }
}

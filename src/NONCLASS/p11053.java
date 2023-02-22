package NONCLASS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p11053 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int[] dp = new int[N];
        StringTokenizer st= new StringTokenizer(br.readLine());

        int maxlength = 0;
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
            //이전 numbers 요소 중 i 번째 값보다 작은 값 찾기
            //i번째 작은 값들의 dp중 최댓값 찾기
            //dp의 i번째 값에 최댓값 + 1 저장
            int length = 0;
            for(int j=0; j<i; j++){
                if(numbers[i] > numbers[j] && dp[j]>length){
                    length = dp[j];
                }
            }
            dp[i] = length+1;
            if(maxlength < dp[i]){
                maxlength = dp[i];
            }
        }
//        System.out.println(Arrays.toString(numbers));
//        System.out.println(Arrays.toString(dp));
        System.out.println(maxlength);

    }
}

package Y2024.M01;


import java.io.*;
import java.util.*;

public class p2512 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //3~10,000
        int[] budget = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = 0;
        int budget_max = 0;
        for(int i=0; i<N; i++){
            budget[i] = Integer.parseInt(st.nextToken());//1~100,000
            if(budget[i]>budget_max) budget_max = budget[i];
            total += budget[i]; //total은 Integer_MAX를 넘지 x
        }
        int M = Integer.parseInt(br.readLine());//3~1,000,000,000

        //budget total이 M보다 작거나 같으면 -> total 그대로 출력
        //그렇지 않으면 -> budget total을 최대한 높게 만드는 상한선 찾기 -> 1~Max value 이분탐색
        if(total<=M){
            System.out.println(budget_max);
            return;
        }

        int low = 1, high = budget_max;
        int max_cost = 0, max_sum = 0;
        while(low<=high){
            int mid = (low+high)/2;
            int sum = 0;
            for(int i=0; i<N; i++){
                if(budget[i]<=mid) sum += budget[i];
                else sum += mid;
            }
            if(sum<=M){
                //budget 만족하는 경우.
                //mid가 상한선 max 보다 높은지 확인하고 업데이트
                if(mid>max_cost){
                    max_cost = mid;
                }
                low = mid+1;
            }else{
                //budget 초과하는 경우. -> mid를 내려주기
                high = mid-1;
            }
        }
        System.out.println(max_cost);


    }
}

package Y2024.M01;

import java.io.*;
import java.util.*;

public class p14888 {
    static int[] numbers, operators;
    static int n;
    static int maxN = Integer.MIN_VALUE;
    static int minN = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        operators = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            operators[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<4; i++){
            //numbers[0], numbers[1]을 + - * / 중에 수행
            if(operators[i]>0){
                //사칙연산 가능하면 수행하기
                operators[i]--;
                if(i==0){
                    //덧셈
                    dfs(numbers[0]+numbers[1], 2);
                }else if(i==1){
                    dfs(numbers[0]-numbers[1], 2);
                }else if(i==2){
                    dfs(numbers[0]*numbers[1], 2);
                }else{
                    dfs(numbers[0]/numbers[1], 2);
                }
                operators[i]++;
            }

        }
        System.out.println(maxN);
        System.out.println(minN);


    }

    static void dfs(int prior, int idx){
        //앞 연산 결과 prior에 저장, idx에서 prior 다음 num의 idx 지정
        if(idx==n){
            maxN = Math.max(maxN, prior);
            minN = Math.min(minN, prior);
            return;
        }

        for(int i=0; i<4; i++){
            //numbers[0], numbers[1]을 + - * / 중에 수행
            if(operators[i]>0){
                //사칙연산 가능하면 수행하기
                operators[i]--;
                if(i==0){
                    //덧셈
                    dfs(prior+numbers[idx], idx+1);
                }else if(i==1){
                    dfs(prior-numbers[idx], idx+1);
                }else if(i==2){
                    dfs(prior*numbers[idx], idx+1);
                }else{
                    dfs(prior/numbers[idx], idx+1);
                }
                operators[i]++;
            }

        }

    }
}

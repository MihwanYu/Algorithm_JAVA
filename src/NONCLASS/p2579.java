package NONCLASS;

import java.io.*;
import java.util.*;

public class p2579 {
    static int N;
    static int[] numbers;
    public static void main(String[] args) throws Exception{
        //dp 재활 필요 ,,,,
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N+1];
        for(int i=1; i<=N; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] answer = new int[N+1];
        //계단 n -> n+1 또는 n+2로 이동 가능
        //연속 3개 계단 불가능 => 계단 x에 가려면 x-3 -> x-1(2칸) -> x(1칸), x-2 -> x(2칸) 오르는 방법 2가지
        //마지막 계단 무조건

        answer[1] = numbers[1];

        if(N>1){
            answer[2] = answer[1] + numbers[2];
        }

//        answer[3] = numbers[1]+numbers[3] vs numbers[2]+numbers[3];

        for(int i=3; i<=N; i++){// i: 0..N-1

            answer[i] += Math.max(answer[i-3] + numbers[i-1], answer[i-2]) + numbers[i];

        }
//        System.out.println(Arrays.toString(answer));
        System.out.println(answer[N]);

    }


}

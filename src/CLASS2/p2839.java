package CLASS2;

import java.io.*;
import java.util.*;

public class p2839 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        //3kg, 5kg짜리 봉지로 운반할 때 봉지의 최소 개수 / 불가능하면 -1 출력

        int[] arr = new int[N+1];
        Arrays.fill(arr, -1);

        arr[3] = 1;
        if(N>=5) arr[5] = 1;

        //6 = 3+3
        //8 = 3+5
        //10 = 5+5
        //11 = 3+3+5
        //12 = 3+3+3+3
        //13 = 3+5+5
        //14 = 3+3+3+5
        //15 = 5+5+5 vs 3+3+3+3+3


        if(N<=5){//입력 숫자가 3,4,5중 하나면 바로 리턴
            System.out.println(arr[N]);
            return;
        }

        //6이상일때
        for(int i=6; i<=N; i++){
            int add3 = arr[i-3]!=-1 ? arr[i-3]+1 : -1;
            int add5 = arr[i-5]!=-1 ? arr[i-5]+1 : -1;

            if(add3==-1){
                arr[i] = add5==-1 ? -1 : add5;
            }else{
                if(add5==-1) arr[i] = add3;
                else arr[i] = add3<add5 ? add3 : add5;
            }

        }
//        System.out.println(Arrays.toString(arr));
        System.out.println(arr[N]);




    }
}

package CLASS2;

import java.io.*;
import java.util.*;

public class p1929 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] arr = new boolean[N+1];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;

        //에라토스테네스의 체?
        for(int i=2; i<=N; i++){
            if( !arr[i] ) continue;
            //소소 i에 대해서만 i의 배수는 모두 false처리
            for(int k=2; k<=N/i; k++){
                arr[i*k] = false;
            }
        }

        for(int i=M; i<=N; i++){
            if(arr[i]) System.out.println(i);
        }


    }
}

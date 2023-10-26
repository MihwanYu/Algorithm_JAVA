package CLASS3;

import java.io.*;
import java.util.*;

public class p18870re {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] arr_sorted = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            arr_sorted[i] = arr[i];
        }

        Arrays.sort(arr_sorted);

        //객체로 바궈서.... 풀깅... 아니 시프트 키 왜 안먹힘?



    }

}



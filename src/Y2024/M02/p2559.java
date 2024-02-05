package Y2024.M02;

import java.io.*;
import java.util.*;

public class p2559 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().split(" ");
        int N = Integer.parseInt(first[0]);
        int K = Integer.parseInt(first[1]);
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(i<K) sum += arr[i];
        }

        int max = sum;
        for(int i=K; i<N; i++){
            sum = sum+arr[i]-arr[i-K];
            max = Math.max(max, sum);
        }
        System.out.println(max);

    }
}

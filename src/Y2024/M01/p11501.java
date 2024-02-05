package Y2024.M01;

import java.io.*;
import java.util.*;
public class p11501 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int t=0; t<tc; t++){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int maxIdx = n-1;
            long profit = 0;

            for(int i=n-1; i>=0; i--){
                if(arr[i]>=arr[maxIdx]){
                    maxIdx = i;
                }else{
                    profit += (arr[maxIdx]-arr[i]);
                }

            }
            System.out.println(profit);
        }
    }
}

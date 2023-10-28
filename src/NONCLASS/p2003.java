package NONCLASS;

import java.io.*;
import java.util.*;
public class p2003 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // arr[i]...arr[j] 합이 M일 경우의 수

        // arr[idx1]...arr[idx2 - 1] 합이 M이면 count++
        // M보다 크면 idx1 ++
        // M보다 작으면 idx2 ++

        int count = 0;
        int idx1 = 0, idx2 = 1;
        int partialsum = arr[idx1];

        while(idx2 <= N){

            if(partialsum==M){
                System.out.println("idx("+idx1+")"+arr[idx1]+"...idx("+(idx2-1)+")"+arr[idx2-1]);
                count++;
                if(idx2==N) break;
                partialsum -= arr[idx1];
                partialsum += arr[idx2];
                idx1++;
                idx2++;
            }else if(partialsum < M){
                if(idx2==N) break;
                partialsum += arr[idx2];
                idx2++;
            }else{
                partialsum -= arr[idx1];
                idx1++;
            }
        }

        System.out.println(count);






    }
}

package CLASS2;

import java.io.*;
import java.util.*;
public class p18110 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int startIdx = (int)Math.round(N*0.15);
        int endIdx = N-(int)Math.round(N*0.15);

        int total = 0;
        for(int i=startIdx; i<endIdx; i++){
            total += arr[i];
        }
//        System.out.println("15%: "+startIdx+ " , total: "+total);
        int rank = (int)Math.round(total/(double)(N-startIdx*2));
        System.out.println(rank);


    }

}

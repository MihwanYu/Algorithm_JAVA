package CLASS3;

import java.io.*;
import java.util.*;

public class p1463 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //1 ~ 1 000 000
        int[] arr = new int[N+1];
        Arrays.fill(arr, Integer.MAX_VALUE);

        arr[N] = 0;
        arr[N-1] = 1;
        if(N%2==0) arr[N/2] = 1;
        if(N%3==0) arr[N/3] = 1;

        for(int i=N-2; i>0; i--){
            if(arr[i]>arr[i+1]) arr[i] = arr[i+1]+1;
            if(i*2<=N && arr[i]>arr[i*2]) arr[i] = arr[i*2]+1;
            if(i*3<=N && arr[i]>arr[i*3]) arr[i] = arr[i*3]+1;

        }
//        System.out.println(Arrays.toString(arr));
        System.out.println(arr[1]);


    }

}

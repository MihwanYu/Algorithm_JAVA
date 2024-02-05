package Y2024.M01;

import java.io.*;
import java.util.*;
public class p11727 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] answer = new int[N+1];
        answer[1] = 1;
        for(int i=2; i<N+1; i++){
            if(i==2){
                answer[i] = 3;
            }else{
                answer[i] = (answer[i-2]*2 + answer[i-1])%10007;
            }
        }
        System.out.println(answer[N]);


    }
}

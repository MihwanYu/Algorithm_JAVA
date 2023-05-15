package CLASS3;

import java.io.*;
import java.util.*;
public class p11399 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        int count = 0;
        for(int i=0; i<N; i++){
            count += numbers[i]*(N-i);
        }
        System.out.println(count);



    }
}

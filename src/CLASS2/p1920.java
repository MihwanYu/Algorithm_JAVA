package CLASS2;

import java.io.*;
import java.util.*;
public class p1920 {
    static int[] numbers;
    public static void main(String[] args) throws Exception{
        //N개의 정수들이 주어졌을 때, 이 안에 X라는 정수가 존재하는가?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            boolean exist = findNumber(Integer.parseInt(st.nextToken()));
            if(exist) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());

    }

    static boolean findNumber(int n){
        int low = 0, high = numbers.length - 1;
        while(low<=high){
            int mid = (low+high)/2;
            if(numbers[mid]==n) return true;
            else if(numbers[mid]<n){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }


        return false;
    }
}

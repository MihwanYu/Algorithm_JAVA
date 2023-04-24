package CLASS4;

import java.io.*;
import java.util.*;

public class p15657 {
    static int N, M;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder("");
    public static void main(String[] args) throws Exception{
        //N개 자연수 중 M개 고르기, 중복 선택 가능, 비내림차순(=> 오름차순, 중복허용)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        //sort numbers
        Arrays.sort(numbers);

        //dfs
        int[] ans = new int[M];
        for(int i=0; i<N; i++){
            ans[0] = numbers[i];
            dfs(ans, 1, i);
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());

    }

    public static void dfs(int[] ans, int idx, int valIdx){
        if(idx==M){
            for(int num: ans){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=valIdx; i<N; i++){
            ans[idx] = numbers[i];
            dfs(ans, idx+1, i);
        }
    }
}

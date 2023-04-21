package CLASS4;

import java.io.*;
import java.util.*;

public class p15654 {
    static int n, m;
    static int[] numbers;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        numbers = new int[n];

        st= new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            numbers[i]= Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        //n개의 자연수 중 m개를 고른 수열
        //사전 순으로 증가하는 순서
        int[] seq = new int[m];
        boolean[] idx_avail = new boolean[n];
        for(int i=0; i<n; i++){
            seq[0] = numbers[i];
            idx_avail[i] = true;
            dfs(seq, idx_avail, 1, numbers[i]);//seq 배열에 1개 값 들어갔고 가장 마지막 값은 i이다
            idx_avail[i] = false;
        }

    }

    static void dfs(int[] seq, boolean[] idx_avail, int idx, int val){
        if(idx==m){
            StringBuilder sb = new StringBuilder("");
            for(int n:seq){
                sb.append(n).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
            return;
        }

        //1 7 8 9
        //idx = 1, numbers[i] = 1  {7,...}
        //idx = 1, numbers[i] = 7  {1,...}

        for(int i=0; i<n; i++){
            if(idx_avail[i]) continue;
            seq[idx] = numbers[i];
            idx_avail[i] = true;
            dfs(seq, idx_avail, idx+1, numbers[i]);
            idx_avail[i] = false;
        }


    }
}

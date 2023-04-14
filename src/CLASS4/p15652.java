package CLASS4;

import java.io.*;
import java.util.*;
public class p15652 {
    static int N, M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()) ;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++){
            int[] arr = new int[M];
            arr[0] = i;
            dfs(i, arr, 1);//i를 넣을 경우{1,...}  {2,...}
        }
    }

    static void dfs(int n, int[] arr, int cnt){
        if(cnt==M){
            StringBuilder sb = new StringBuilder();
            for(int num:arr){
                sb.append(num).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
            return;
        }

        for(int i=n; i<=N; i++){
            arr[cnt] = i;
            dfs(i, arr, cnt+1);//i를 넣을 경우
        }

    }
}
